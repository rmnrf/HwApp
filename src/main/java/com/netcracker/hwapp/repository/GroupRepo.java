package com.netcracker.hwapp.repository;

import com.netcracker.hwapp.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepo extends JpaRepository<Group, Long> {
    Group findByFacultyIdAndNumber(Long facultyId, Integer groupNumber);
}
