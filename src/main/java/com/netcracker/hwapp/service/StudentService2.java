package com.netcracker.hwapp.service;

import com.netcracker.hwapp.dto.*;
import com.netcracker.hwapp.exception.UserAlreadyExistsException;
import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.Student;
import com.netcracker.hwapp.repository.StudentRepo;
import com.netcracker.hwapp.repository.UserRepo;
import com.netcracker.hwapp.util.CopyUtils;
import com.netcracker.hwapp.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService2 {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private StudentRepo studentRepo;

    public Student create(Student student) throws UserAlreadyExistsException {
        if (userRepo.findByEmail(student.getEmail()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует.");
        }
        userRepo.save(student);
        return student;
    }

    public Student update(Student student, Long id) throws UserNotFoundException {
        Optional<Student> oldStudent = studentRepo.findById(id);
        if (oldStudent.isEmpty()) {
            throw new UserNotFoundException("Такого пользователя не существует.");
        }
        var studentEntity = oldStudent.get();
        CopyUtils.copyProperties(student, studentEntity);
        studentRepo.save(studentEntity);
        return studentEntity;
    }

    public DTOEntity read(Long id) throws UserNotFoundException {
        Optional<Student> oldStudent = studentRepo.findById(id);
        if (oldStudent.isEmpty()) {
            throw new UserNotFoundException("Такого пользователя не существует.");
        }
        return new DtoUtils().convertToDto(oldStudent.get(), new StudentReadDTO());
    }

    public Long delete(Long id) {
        studentRepo.deleteById(id);
        return id;
    }
}
