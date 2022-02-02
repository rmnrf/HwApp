package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findAllByGroups_Id(Long id);
    List<Task> findAllByTeacherEmail(String email);
}
