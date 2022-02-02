package com.netcracker.hwapp.service;

import com.netcracker.hwapp.dto.StudentRegistrationDto;
import com.netcracker.hwapp.dto.TeacherRegistrationDto;
import com.netcracker.hwapp.dto.UserGetDto;
import com.netcracker.hwapp.dto.UserUpdateDto;
import com.netcracker.hwapp.exception.GroupNotFoundException;
import com.netcracker.hwapp.exception.UserAlreadyExistsException;
import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.Student;
import com.netcracker.hwapp.model.Teacher;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;

public interface UserService extends UserDetailsService {
    Teacher create(TeacherRegistrationDto teacherRegistrationDto)
            throws UserAlreadyExistsException;
    Student create(StudentRegistrationDto studentRegistrationDto)
            throws UserAlreadyExistsException, GroupNotFoundException;
    void update(UserUpdateDto dto, Principal principal);
    UserGetDto findByEmail(String email) throws UserNotFoundException;
    UserGetDto findById(Long id) throws UserNotFoundException;
}
