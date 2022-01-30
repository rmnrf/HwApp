package com.netcracker.hwapp.service.impl;

import com.netcracker.hwapp.model.Teacher;
import com.netcracker.hwapp.repository.TeacherRepo;
import com.netcracker.hwapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    @Override
    public List<Teacher> findAll() {
        return teacherRepo.findAll();
    }
}
