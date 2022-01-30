package com.netcracker.hwapp.dto;

import com.netcracker.hwapp.model.Discipline;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DisciplineReadDto {

    private Long id;
    private String name;

    public static DisciplineReadDto mapToDto(Discipline entity) {
        DisciplineReadDto dto = new DisciplineReadDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
