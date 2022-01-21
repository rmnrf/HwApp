package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<TaskEntity, Long> {
}
