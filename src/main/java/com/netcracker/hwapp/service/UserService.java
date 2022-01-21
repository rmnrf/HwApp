package com.netcracker.hwapp.service;

import com.netcracker.hwapp.entity.UserEntity;
import com.netcracker.hwapp.exception.UserAlreadyExistsException;
import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.User;
import com.netcracker.hwapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistsException {
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует.");
        }
        return userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        Optional<UserEntity> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Такого пользователя не существует.");
        }
        return User.toModel(user.get());
    }

    public Long delete(Long id) {
        userRepo.deleteById(id);
        return id;
    }
}
