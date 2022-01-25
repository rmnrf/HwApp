package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolutionRepo extends JpaRepository<Solution, Long> {
    List<Solution> findAllByTaskTeacherId(Long id);
}
