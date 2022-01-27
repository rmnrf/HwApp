package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.dto.UserCreateDTO;
import com.netcracker.hwapp.dto.UserUpdateDTO;
import com.netcracker.hwapp.exception.UserAlreadyExistsException;
import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.service.UserServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    @Autowired
    private UserServiceImpl2 userService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        try {
            userService.createUser(userCreateDTO);
            return ResponseEntity.ok("Пользователь успешно зарегистрирован.");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO,
                                        @RequestParam Long userId) {
        try {
            userService.updateUser(userUpdateDTO, userId);
            return ResponseEntity.ok("Данные пользователя успешно изменены.");
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }

    @GetMapping
    public ResponseEntity<?> readUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.readUser(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.deleteUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка.");
        }
    }
}
