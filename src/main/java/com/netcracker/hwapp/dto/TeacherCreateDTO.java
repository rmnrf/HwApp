package com.netcracker.hwapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCreateDTO extends UserCreateDTO {
    @NotEmpty(message = "Укажите преподаваемые дисциплины")
    private Set<DisciplineDTO> disciplines;
}
