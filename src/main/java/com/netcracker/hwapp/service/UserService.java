package com.netcracker.hwapp.service;

import com.netcracker.hwapp.dto.DTOEntity;
import com.netcracker.hwapp.dto.UserCreateDTO;
import com.netcracker.hwapp.dto.UserReadDTO;
import com.netcracker.hwapp.dto.UserUpdateDTO;
import com.netcracker.hwapp.exception.UserAlreadyExistsException;
import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.User;
import com.netcracker.hwapp.repository.UserRepo;
import com.netcracker.hwapp.util.CopyUtils;
import com.netcracker.hwapp.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserCreateDTO createUser(UserCreateDTO userCreateDTO) throws UserAlreadyExistsException {
        User user = new User();
        CopyUtils.copyProperties(userCreateDTO, user);
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует.");
        }
        userRepo.save(user);
        return (UserCreateDTO) new DtoUtils().convertToDto(user, new UserCreateDTO());
    }

    public UserUpdateDTO updateUser(UserUpdateDTO userUpdateDTO, Long id) throws UserNotFoundException {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Такого пользователя не существует.");
        }
        User userEntity = (User) new DtoUtils().convertToEntity(user.get(), userUpdateDTO);
        //var userEntity = user.get();
        //CopyUtils.copyProperties(userDTO, userEntity);
        userRepo.save(userEntity);
        return (UserUpdateDTO) new DtoUtils().convertToDto(user.get(), new UserUpdateDTO());
    }

    public DTOEntity readUser(Long id) throws UserNotFoundException {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Такого пользователя не существует.");
        }
        return new DtoUtils().convertToDto(user.get(), new UserReadDTO());
    }

    public Long deleteUser(Long id) {
        userRepo.deleteById(id);
        return id;
    }
}
