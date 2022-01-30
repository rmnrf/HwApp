package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.exception.UserAlreadyExistsException;
import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Teacher teacher) {
        try {
            //teacherService.create(teacher);
            return ResponseEntity.ok("Преподаватель успешно зарегистрирован.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Teacher teacher,
                                           @RequestParam Long userId) {
        try {
            //teacherService.update(teacher, userId);
            return ResponseEntity.ok("Данные преподавателя успешно изменены.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }

    @GetMapping
    public ResponseEntity<?> read(@RequestParam Long id) {
        try {
            //return ResponseEntity.ok(teacherService.read(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            //return ResponseEntity.ok(teacherService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
        return null;
    }
}
