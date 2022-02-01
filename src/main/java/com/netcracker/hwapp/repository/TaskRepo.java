package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.model.Task;
import com.netcracker.hwapp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    //List<Task> findAllByGroupsId(Long id);
    List<Task> findAllByGroups_Id(Long id);
    List<Task> findAllByTeacherId(Long id);
    List<Task> findAllByTeacherEmail(String email);
}
