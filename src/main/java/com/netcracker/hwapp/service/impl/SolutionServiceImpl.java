package com.netcracker.hwapp.service.impl;

import com.netcracker.hwapp.dto.*;
import com.netcracker.hwapp.enums.Grade;
import com.netcracker.hwapp.enums.Status;
import com.netcracker.hwapp.exception.DeadlineDateTimeHasExriped;
import com.netcracker.hwapp.exception.SolutionAlreadyExistsException;
import com.netcracker.hwapp.model.File;
import com.netcracker.hwapp.model.Solution;
import com.netcracker.hwapp.repository.FileRepo;
import com.netcracker.hwapp.repository.SolutionRepo;
import com.netcracker.hwapp.repository.StudentRepo;
import com.netcracker.hwapp.repository.TaskRepo;
import com.netcracker.hwapp.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class SolutionServiceImpl implements SolutionService {

    @Autowired
    private FileRepo fileRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private SolutionRepo solutionRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public void create(SolutionCreateDto dto, Principal principal, MultipartFile myFile)
            throws SolutionAlreadyExistsException, DeadlineDateTimeHasExriped, IOException {
        var task = taskRepo.findById(dto.getTask().getId()).get();
        if (solutionRepo.findByTaskIdAndStudentEmail(dto.getTask().getId(), principal.getName()).isPresent()) {
            throw new SolutionAlreadyExistsException("Решение уже существует.");
        }
        if (LocalDateTime.now().isAfter(task.getDeadlineDateTime())) {
            throw new DeadlineDateTimeHasExriped("Время отправки решений к текущему заданию вышло.");
        }
        Solution solution = new Solution();
        solution.setTask(taskRepo.findById(dto.getTask().getId()).get());
        solution.setStudent(studentRepo.findByEmail(principal.getName()).get());
        solution.setComment(dto.getComment());
        solution.setGrade(Grade.NONE);
        solution.setStatus(Status.CREATED);
        solution.setStatus(Status.SENT);

        File file = new File();
        file.setName(myFile.getOriginalFilename());
        file.setType(myFile.getContentType());
        file.setData(myFile.getBytes());
        fileRepo.save(file);

        solution.setFile(file);
        solutionRepo.save(solution);
    }

    @Override
    public void update(SolutionUpdateDto dto, Principal principal, MultipartFile myFile) throws IOException {
        Solution solution = solutionRepo.findById(dto.getId()).get();
        solution.setComment(dto.getComment());
        if (solutionRepo.findAllByStudentEmail(principal.getName()).contains(solution)) {
            if (solution.getGrade().equals(Grade.NONE)) {
                File file = new File();
                file.setName(myFile.getOriginalFilename());
                file.setType(myFile.getContentType());
                file.setData(myFile.getBytes());
                fileRepo.save(file);

                solution.setFile(file);
                solutionRepo.save(solution);
            } else {
                throw new RuntimeException("Вы не можете удалить оцененное задание.");
            }
        } else {
            throw new RuntimeException("Вы не можете удалить это решение.");
        }
    }

    @Override
    public void delete(Long id, Principal principal) {
        Solution solution = solutionRepo.findById(id).get();
        if (solutionRepo.findAllByStudentEmail(principal.getName()).contains(solution)) {
            if (solution.getGrade().equals(Grade.NONE)) {
                solutionRepo.delete(solution);
            } else {
                throw new RuntimeException("Вы не можете удалить оцененное задание.");
            }
        } else {
            throw new RuntimeException("Вы не можете удалить это решение.");
        }
    }

    @Override
    public void estimate(SolutionEstimateDto dto, Principal principal) {
        Solution solution = solutionRepo.findById(dto.getId()).get();
        if (solutionRepo.findAllByTaskTeacherEmail(principal.getName()).contains(solution)) {
            solution.setGrade(dto.getGrade());
            solution.setStatus(Status.CHECKED);
            solutionRepo.save(solution);
        } else {
            throw new RuntimeException("Вы не можете оценивать это решение.");
        }
    }
}
