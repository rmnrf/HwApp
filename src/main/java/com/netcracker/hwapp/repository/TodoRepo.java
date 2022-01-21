package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<TodoEntity, Long> {
}
