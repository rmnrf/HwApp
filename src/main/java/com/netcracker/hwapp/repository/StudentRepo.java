package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
}
