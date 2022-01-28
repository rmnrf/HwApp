package com.netcracker.hwapp.service;

import com.netcracker.hwapp.dto.StudentRegistrationDto;
import com.netcracker.hwapp.dto.TeacherRegistrationDto;
import com.netcracker.hwapp.enums.Role;
import com.netcracker.hwapp.exception.UserAlreadyExistsException;
import com.netcracker.hwapp.model.*;
import com.netcracker.hwapp.repository.GroupRepo;
import com.netcracker.hwapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Teacher create(TeacherRegistrationDto teacherRegistrationDto) throws UserAlreadyExistsException {
        if (userRepo.findByEmail(teacherRegistrationDto.getEmail()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует.");
        }
        Teacher teacher = new Teacher();
        teacher.setEmail(teacherRegistrationDto.getEmail());
        teacher.setPassword(passwordEncoder.encode(teacherRegistrationDto.getPassword()));
        teacher.setFirstName(teacherRegistrationDto.getFirstName());
        teacher.setLastName(teacherRegistrationDto.getLastName());
        teacher.setMiddleName(teacherRegistrationDto.getMiddleName());
        teacher.setDisciplines(teacherRegistrationDto.getDisciplines());
        teacher.setRole(Role.teacher);
        userRepo.save(teacher);
        return teacher;
    }

    @Override
    public Student create(StudentRegistrationDto studentRegistrationDto) throws UserAlreadyExistsException {
        if (userRepo.findByEmail(studentRegistrationDto.getEmail()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует.");
        }
        Student student = new Student();
        student.setEmail(studentRegistrationDto.getEmail());
        student.setPassword(passwordEncoder.encode(studentRegistrationDto.getPassword()));
        student.setFirstName(studentRegistrationDto.getFirstName());
        student.setLastName(studentRegistrationDto.getLastName());
        student.setMiddleName(studentRegistrationDto.getMiddleName());
        student.setGroup(getGroupByFacultyNameAndGroupNumber(studentRegistrationDto.getFaculty(),
                studentRegistrationDto.getGroup().getNumber()));
        student.setRole(Role.student);
        userRepo.save(student);
        return student;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Неверный email или пароль.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.getRole().getAuthorities());
    }

    public Group getGroupByFacultyNameAndGroupNumber(Faculty faculty, Integer groupNumber) {
        return groupRepo.findByFacultyNameAndNumber(faculty, groupNumber);
    }
}
