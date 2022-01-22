package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    Teacher findByEmail(String email);
}
