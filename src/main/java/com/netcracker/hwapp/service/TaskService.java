package com.netcracker.hwapp.service;

import com.netcracker.hwapp.entity.TaskEntity;
import com.netcracker.hwapp.entity.UserEntity;
import com.netcracker.hwapp.exception.TaskNotFoundException;
import com.netcracker.hwapp.model.Task;
import com.netcracker.hwapp.model.Todo;
import com.netcracker.hwapp.repository.TaskRepo;
import com.netcracker.hwapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private UserRepo userRepo;

    public Task create(TaskEntity task, Long userId) {
        UserEntity user = userRepo.findById(userId).get();
        task.setCreator(user);
        return Task.toModel(taskRepo.save(task));
    }

    public Task getOne(Long id) throws TaskNotFoundException {
        Optional<TaskEntity> task = taskRepo.findById(id);
        if (task.isEmpty()) {
            throw new TaskNotFoundException("Такого задания не существует.");
        }
        return Task.toModel(task.get());
    }

    public List<Task> getAll() {
        return taskRepo.findAll().stream().map(Task::toModel)
                .collect(Collectors.toList());
    }

    public Task update(TaskEntity task, Long id) throws TaskNotFoundException {
        Optional<TaskEntity> ourTask = taskRepo.findById(id);
        if (ourTask.isEmpty()) {
            throw new TaskNotFoundException("Такого задания не существует.");
        }
        task.setId(id);
        return Task.toModel(taskRepo.save(task));
    }

    public Long delete(Long id) {
        taskRepo.deleteById(id);
        return id;
    }
}
