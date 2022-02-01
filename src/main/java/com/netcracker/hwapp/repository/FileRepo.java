package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface FileRepo extends JpaRepository<File, Long> {
    Optional<File> findByName(String filename);
}
