package com.netcracker.hwapp.service.impl;

import com.netcracker.hwapp.dto.DisciplineReadDto;
import com.netcracker.hwapp.repository.DisciplineRepo;
import com.netcracker.hwapp.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisciplineServiceImpl implements DisciplineService {

    @Autowired
    private DisciplineRepo disciplineRepo;

    @Override
    public List<DisciplineReadDto> findAll() {
        return disciplineRepo.findAll().stream().map(DisciplineReadDto::mapToDto).collect(Collectors.toList());
    }
}
