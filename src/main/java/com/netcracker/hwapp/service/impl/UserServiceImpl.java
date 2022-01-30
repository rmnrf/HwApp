package com.netcracker.hwapp.service.impl;

import com.netcracker.hwapp.dto.StudentRegistrationDto;
import com.netcracker.hwapp.dto.TeacherRegistrationDto;
import com.netcracker.hwapp.dto.UserGetDto;
import com.netcracker.hwapp.dto.UserUpdateDto;
import com.netcracker.hwapp.enums.Role;
import com.netcracker.hwapp.exception.GroupNotFoundException;
import com.netcracker.hwapp.exception.UserAlreadyExistsException;
import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.*;
import com.netcracker.hwapp.repository.GroupRepo;
import com.netcracker.hwapp.repository.UserRepo;
import com.netcracker.hwapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Teacher create(TeacherRegistrationDto teacherRegistrationDto)
            throws UserAlreadyExistsException {
        if (userRepo.findByEmail(teacherRegistrationDto.getEmail()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует.");
        }
        Teacher teacher = mapToEntity(teacherRegistrationDto);
        userRepo.save(teacher);
        return teacher;
    }

    @Override
    public Student create(StudentRegistrationDto studentRegistrationDto)
            throws UserAlreadyExistsException, GroupNotFoundException {
        if (userRepo.findByEmail(studentRegistrationDto.getEmail()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует.");
        }
        Student student = mapToEntity(studentRegistrationDto);
        userRepo.save(student);
        return student;
    }

    @Override
    public void update(UserUpdateDto dto, Principal principal) {
        User user = userRepo.findByEmail(principal.getName());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setMiddleName(dto.getMiddleName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Неверный email или пароль.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.getRole().getAuthorities()
        );
    }

    private Teacher mapToEntity(TeacherRegistrationDto teacherRegistrationDto) {
        Teacher teacher = new Teacher();
        teacher.setEmail(teacherRegistrationDto.getEmail());
        teacher.setPassword(passwordEncoder.encode(teacherRegistrationDto.getPassword()));
        teacher.setFirstName(teacherRegistrationDto.getFirstName());
        teacher.setLastName(teacherRegistrationDto.getLastName());
        teacher.setMiddleName(teacherRegistrationDto.getMiddleName());
        teacher.setDisciplines(teacherRegistrationDto.getDisciplines());
        teacher.setRole(Role.TEACHER);
        return teacher;
    }

    private Student mapToEntity(StudentRegistrationDto studentRegistrationDto)
            throws GroupNotFoundException {
        Student student = new Student();
        student.setEmail(studentRegistrationDto.getEmail());
        student.setPassword(passwordEncoder.encode(studentRegistrationDto.getPassword()));
        student.setFirstName(studentRegistrationDto.getFirstName());
        student.setLastName(studentRegistrationDto.getLastName());
        student.setMiddleName(studentRegistrationDto.getMiddleName());

        Group group = groupRepo.findByFacultyIdAndNumber(studentRegistrationDto.getFaculty().getId(),
                studentRegistrationDto.getGroupNumber());
        if (group == null) {
            throw new GroupNotFoundException("Такой группы не существует.");
        }

        student.setGroup(group);
        student.setRole(Role.STUDENT);
        return student;
    }

    @Override
    public UserGetDto findByEmail(String email) throws UserNotFoundException {
        User entity = userRepo.findByEmail(email);
        if (entity == null) {
            throw new UserNotFoundException("Пользователь с таким e-mail не найден.");
        }
        UserGetDto dto = UserGetDto.mapToDto(entity);
        return dto;
    }

    @Override
    public UserGetDto findById(Long id) throws UserNotFoundException {
        Optional<User> optional = userRepo.findById(id);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new UserNotFoundException("Пользователь с идентификатором " + id + " не найден.");
        }
        return UserGetDto.mapToDto(user);
    }
}
