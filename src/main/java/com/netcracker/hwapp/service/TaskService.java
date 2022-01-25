package com.netcracker.hwapp.service;

import com.netcracker.hwapp.model.Task;
import com.netcracker.hwapp.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepo taskRepo;

    public List<Task> getTasksByUserId(Long id) {
        return taskRepo.findAllByTeacherId(id);
    }
}
