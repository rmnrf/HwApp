package com.netcracker.hwapp.service;

import com.netcracker.hwapp.dto.*;
import com.netcracker.hwapp.exception.UserAlreadyExistsException;
import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.Teacher;
import com.netcracker.hwapp.repository.TeacherRepo;
import com.netcracker.hwapp.repository.UserRepo;
import com.netcracker.hwapp.util.CopyUtils;
import com.netcracker.hwapp.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TeacherRepo teacherRepo;

    public Teacher create(Teacher teacher) throws UserAlreadyExistsException {
        //Teacher teacher = new Teacher();
        //CopyUtils.copyProperties(teacher2, teacher);
        if (userRepo.findByEmail(teacher.getEmail()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует.");
        }
        userRepo.save(teacher);
        return teacher;
    }

    public Teacher update(Teacher teacher, Long id) throws UserNotFoundException {
        Optional<Teacher> oldTeacher = teacherRepo.findById(id);
        if (oldTeacher.isEmpty()) {
            throw new UserNotFoundException("Такого пользователя не существует.");
        }
        var teacherEntity = oldTeacher.get();
        CopyUtils.copyProperties(teacher, teacherEntity);
        teacherRepo.save(teacherEntity);
        return teacherEntity;
    }

    public DTOEntity read(Long id) throws UserNotFoundException {
        Optional<Teacher> teacher = teacherRepo.findById(id);
        if (teacher.isEmpty()) {
            throw new UserNotFoundException("Такого пользователя не существует.");
        }
        return new DtoUtils().convertToDto(teacher.get(), new TeacherReadDTO());
    }

    public Long delete(Long id) {
        teacherRepo.deleteById(id);
        return id;
    }
}
