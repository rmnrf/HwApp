package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplineRepo extends JpaRepository<Discipline, Long> {
}
