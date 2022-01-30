package com.netcracker.hwapp.dto;

import com.netcracker.hwapp.model.Faculty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FacultyReadDto {

    private Long id;
    private String name;

    public static FacultyReadDto mapToDto(Faculty entity) {
        FacultyReadDto dto = new FacultyReadDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
