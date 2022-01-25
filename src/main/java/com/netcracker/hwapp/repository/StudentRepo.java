package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Student> findAllByGroupId(Long id);
}
