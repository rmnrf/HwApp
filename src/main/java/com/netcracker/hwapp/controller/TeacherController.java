package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.dto.TeacherCreateDTO;
import com.netcracker.hwapp.dto.TeacherUpdateDTO;
import com.netcracker.hwapp.exception.UserAlreadyExistsException;
import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.Teacher;
import com.netcracker.hwapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping
    public ResponseEntity<?> createTeacher(@Valid @RequestBody Teacher teacher) {
        try {
            teacherService.createTeacher(teacher);
            return ResponseEntity.ok("Пользователь успешно зарегистрирован.");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTeacher(@Valid @RequestBody Teacher teacher,
                                           @RequestParam Long userId) {
        try {
            teacherService.updateTeacher(teacher, userId);
            return ResponseEntity.ok("Данные пользователя успешно изменены.");
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }

    @GetMapping
    public ResponseEntity<?> readTeacher(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(teacherService.readTeacher(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(teacherService.deleteTeacher(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }
}
