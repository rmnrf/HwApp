package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface SolutionRepo extends JpaRepository<Solution, Long> {
    List<Solution> findAllByTaskTeacherId(Long id);
    List<Solution> findAllByTaskTeacherEmail(String email);
    List<Solution> findAllByStudentEmail(String email);
    List<Solution> findAllByTaskId(Long id);
    Optional<Solution> findByTaskIdAndStudentEmail(Long id, String email);
    void deleteAllByTaskId(Long id);
}
