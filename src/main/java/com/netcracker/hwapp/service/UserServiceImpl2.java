package com.netcracker.hwapp.service;

import com.netcracker.hwapp.dto.DTOEntity;
import com.netcracker.hwapp.dto.UserCreateDTO;
import com.netcracker.hwapp.dto.UserReadDTO;
import com.netcracker.hwapp.exception.UserAlreadyExistsException;
import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.User;
import com.netcracker.hwapp.repository.UserRepo;
import com.netcracker.hwapp.util.CopyUtils;
import com.netcracker.hwapp.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl2 {

    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    public Page<User> findPaginated(Integer pageNumber, Integer pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return userRepo.findAll(pageable);
    }

    public User getUserById(Long id) throws UserNotFoundException {
         Optional<User> optional = userRepo.findById(id);
         User user = null;
         if (optional.isPresent()) {
             user = optional.get();
         } else {
             throw new UserNotFoundException("Пользователь с id " + id + " не найден.");
         }
         return user;
    }

    public UserCreateDTO createUser(UserCreateDTO userCreateDTO) throws UserAlreadyExistsException {
        User user = new User();
        CopyUtils.copyProperties(userCreateDTO, user);
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует.");
        }
        userRepo.save(user);
        return (UserCreateDTO) new DtoUtils().convertToDto(user, new UserCreateDTO());
    }

//    public UserUpdateDTO updateUser(UserUpdateDTO userUpdateDTO, Long id) throws UserNotFoundException {
//        Optional<User> user = userRepo.findById(id);
//        if (user.isEmpty()) {
//            throw new UserNotFoundException("Такого пользователя не существует.");
//        }
//        User userEntity = (User) new DtoUtils().convertToEntity(user.get(), userUpdateDTO);
//        //var userEntity = user.get();
//        //CopyUtils.copyProperties(userDTO, userEntity);
//        userRepo.save(userEntity);
//        return (UserUpdateDTO) new DtoUtils().convertToDto(user.get(), new UserUpdateDTO());
//    }

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
