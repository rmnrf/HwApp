package com.netcracker.hwapp.service.impl;

import com.netcracker.hwapp.dto.TaskCreateDto;
import com.netcracker.hwapp.dto.TaskUpdateDto;
import com.netcracker.hwapp.dto.UserUpdateDto;
import com.netcracker.hwapp.exception.TaskAlreadyExistsException;
import com.netcracker.hwapp.model.Discipline;
import com.netcracker.hwapp.model.Task;
import com.netcracker.hwapp.model.User;
import com.netcracker.hwapp.repository.*;
import com.netcracker.hwapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    GroupRepo groupRepo;

    @Autowired
    DisciplineRepo disciplineRepo;

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    SolutionRepo solutionRepo;

    @Override
    public Task create(TaskCreateDto dto, Principal principal) throws TaskAlreadyExistsException {
        Task task = new Task();
        task.setGroups(List.of(groupRepo.findByFacultyIdAndNumber(dto.getFaculty().getId(), dto.getGroupNumber())));

        // Check teacher has this discipline
        Discipline discipline = disciplineRepo.findById(dto.getDisciplineId()).get();
        List<Discipline> teacherDisciplines = teacherRepo.findByEmail(principal.getName()).getDisciplines();
        if (teacherDisciplines.contains(discipline)) {
            task.setDiscipline(disciplineRepo.findById(dto.getDisciplineId()).get());
        } else {
            throw new RuntimeException("У преподавателя нет такой дисциплины.");
        }

        task.setDescription(dto.getDescription());

        if (dto.getDeadlineDateTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Дедлайн не должен быть раньше текущей даты.");
        } else {
            task.setDeadlineDateTime(dto.getDeadlineDateTime());
        }

        task.setTeacher(teacherRepo.findByEmail(principal.getName()));
        return taskRepo.save(task);
    }

    @Override
    public void update(TaskUpdateDto dto, Principal principal) {
        Task task = taskRepo.findById(dto.getId()).get();
        task.setDescription(dto.getDescription());

        if (dto.getDeadlineDateTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Дедлайн не должен быть раньше текущей даты.");
        } else {
            task.setDeadlineDateTime(dto.getDeadlineDateTime());
        }

        taskRepo.save(task);
    }

    @Override
    public void delete(Long id, Principal principal) {
        Task task = taskRepo.findById(id).get();
        if (taskRepo.findAllByTeacherEmail(principal.getName()).contains(task)) {
            taskRepo.delete(task);
        } else {
            throw new RuntimeException("Вы не можете удалить это задание.");
        }
    }
}
