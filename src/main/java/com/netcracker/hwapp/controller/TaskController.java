package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.entity.TaskEntity;
import com.netcracker.hwapp.exception.TaskNotFoundException;
import com.netcracker.hwapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskEntity task,
                                        @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(taskService.create(task, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }

    @GetMapping
    public ResponseEntity<?> getTask(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(taskService.getOne(id));
        } catch (TaskNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getTasks() {
        try {
            return ResponseEntity.ok(taskService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTask(@RequestBody TaskEntity task,
                                        @RequestParam Long id) {
        try {
            return ResponseEntity.ok(taskService.update(task, id));
        } catch (TaskNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(taskService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
