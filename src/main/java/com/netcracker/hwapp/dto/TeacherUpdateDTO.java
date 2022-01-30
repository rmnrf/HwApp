package com.netcracker.hwapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherUpdateDTO {
    @NotEmpty(message = "Укажите преподаваемые дисциплины")
    private Set<DisciplineDTO> disciplines;
}
