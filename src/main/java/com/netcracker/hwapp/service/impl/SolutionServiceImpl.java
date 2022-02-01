package com.netcracker.hwapp.service.impl;

import com.netcracker.hwapp.dto.*;
import com.netcracker.hwapp.enums.Grade;
import com.netcracker.hwapp.enums.Status;
import com.netcracker.hwapp.exception.DeadlineDateTimeHasExriped;
import com.netcracker.hwapp.exception.SolutionAlreadyExistsException;
import com.netcracker.hwapp.model.Solution;
import com.netcracker.hwapp.repository.SolutionRepo;
import com.netcracker.hwapp.repository.StudentRepo;
import com.netcracker.hwapp.repository.TaskRepo;
import com.netcracker.hwapp.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class SolutionServiceImpl implements SolutionService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private SolutionRepo solutionRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public void create(SolutionCreateDto dto, Principal principal)
            throws SolutionAlreadyExistsException, DeadlineDateTimeHasExriped {
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
        solution.setFile(dto.getFile());
        solution.setComment(dto.getComment());
        solution.setGrade(Grade.NONE);
        solution.setStatus(Status.CREATED);
        solution.setStatus(Status.SENT);
        solutionRepo.save(solution);
    }

    @Override
    public void update(SolutionUpdateDto dto, Principal principal) {
        Solution solution = solutionRepo.findById(dto.getId()).get();
        solution.setFile(dto.getFile());
        solution.setComment(dto.getComment());
        if (solutionRepo.findAllByStudentEmail(principal.getName()).contains(solution)) {
            if (solution.getGrade().equals(Grade.NONE)) {
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
