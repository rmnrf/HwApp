package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.exception.UserAlreadyExistsException;
import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.Student;
import com.netcracker.hwapp.service.StudentService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService2 studentService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Student student) {
        try {
            studentService.create(student);
            return ResponseEntity.ok("Студент успешно зарегистрирован.");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Student student,
                                           @RequestParam Long userId) {
        try {
            studentService.update(student, userId);
            return ResponseEntity.ok("Данные студента успешно изменены.");
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }

    @GetMapping
    public ResponseEntity<?> read(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(studentService.read(userId));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(studentService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }
}
