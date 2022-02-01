package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepo extends JpaRepository<Developer, Long> {
    Optional<Developer> findByEmail(String email);
}
