package com.netcracker.hwapp.service;

import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.Student;

import java.util.List;

public interface StudentService {
    Student findByEmail(String email) throws UserNotFoundException;
    List<Student> findAllByGroupId(Long id);
}
