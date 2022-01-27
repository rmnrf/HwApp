package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeveloperRepo extends JpaRepository<Developer, Long> {
    Optional<Developer> findByEmail(String email);
}
