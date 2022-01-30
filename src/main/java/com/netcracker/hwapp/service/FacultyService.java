package com.netcracker.hwapp.service;

import com.netcracker.hwapp.dto.FacultyReadDto;

import java.util.List;

public interface FacultyService {
    List<FacultyReadDto> findAll();
}
