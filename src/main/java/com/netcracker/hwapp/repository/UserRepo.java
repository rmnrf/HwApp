package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}