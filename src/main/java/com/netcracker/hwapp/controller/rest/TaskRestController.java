package com.netcracker.hwapp.controller.rest;

import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<?> getTasksByUserId(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(taskService.getTasksByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }
}
