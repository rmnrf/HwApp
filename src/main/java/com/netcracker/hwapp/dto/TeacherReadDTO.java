package com.netcracker.hwapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherReadDTO extends UserReadDTO {
    private Set<DisciplineDTO> disciplines;
}
