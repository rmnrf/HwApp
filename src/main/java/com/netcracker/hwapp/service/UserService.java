package com.netcracker.hwapp.service;

import com.netcracker.hwapp.dto.StudentRegistrationDto;
import com.netcracker.hwapp.dto.TeacherRegistrationDto;
import com.netcracker.hwapp.exception.GroupNotFoundException;
import com.netcracker.hwapp.exception.UserAlreadyExistsException;
import com.netcracker.hwapp.model.Student;
import com.netcracker.hwapp.model.Teacher;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Teacher create(TeacherRegistrationDto teacherRegistrationDto)
            throws UserAlreadyExistsException;
    Student create(StudentRegistrationDto studentRegistrationDto)
            throws UserAlreadyExistsException, GroupNotFoundException;
}
