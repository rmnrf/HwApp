package com.netcracker.hwapp.service.impl;

import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.Student;
import com.netcracker.hwapp.repository.StudentRepo;
import com.netcracker.hwapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public Student findByEmail(String email) throws UserNotFoundException {
        Optional<Student> optional = studentRepo.findByEmail(email);
        if (optional.isEmpty()) {
            throw new UserNotFoundException("Такого пользователя не существует.");
        }
        return studentRepo.findByEmail(email).get();
    }

    @Override
    public List<Student> findAllByGroupId(Long id) {
        return studentRepo.findAllByGroupId(id);
    }
}
