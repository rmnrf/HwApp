package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    Teacher findByEmail(String email);
    List<Teacher> findByDisciplines_Id(Long id);
}
