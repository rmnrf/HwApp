package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SolutionRepo extends JpaRepository<Solution, Long> {
    List<Solution> findAllByTaskTeacherId(Long id);
    List<Solution> findAllByTaskTeacherEmail(String email);
    List<Solution> findAllByStudentEmail(String email);
    Optional<Solution> findByTaskIdAndStudentEmail(Long id, String email);
    void deleteAllByTaskId(Long id);
}
