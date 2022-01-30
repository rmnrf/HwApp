package com.netcracker.hwapp.service;

import com.netcracker.hwapp.dto.DisciplineReadDto;

import java.util.List;

public interface DisciplineService {
    List<DisciplineReadDto> findAll();
}
