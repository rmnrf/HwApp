package com.netcracker.hwapp.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskRestController {

    @GetMapping
    public ResponseEntity<?> getTasksByUserId(@RequestParam Long userId) {
        try {
            //return ResponseEntity.ok(taskService.getTasksByUserId(userId));
            return ResponseEntity.ok().body("Good!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }
}
