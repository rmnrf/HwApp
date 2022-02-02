package com.netcracker.hwapp.service;

import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.Student;
import com.netcracker.hwapp.model.Task;

import java.util.List;

public interface StudentService {
    Student findByEmail(String email) throws UserNotFoundException;
    List<Task> findAllUnresolvedTasks(Long studentId);
    List<Student> findAllByGroupId(Long id);
    List<Task> findAllActualTasks(Long studentId);
}
