package com.netcracker.hwapp.service.impl;

import com.netcracker.hwapp.dto.FacultyReadDto;
import com.netcracker.hwapp.repository.FacultyRepo;
import com.netcracker.hwapp.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepo facultyRepo;

    @Override
    public List<FacultyReadDto> findAll() {
        return facultyRepo.findAll().stream().map(FacultyReadDto::mapToDto).collect(Collectors.toList());
    }
}
