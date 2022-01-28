package com.netcracker.hwapp.dto;

import com.netcracker.hwapp.model.Discipline;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class TeacherRegistrationDto extends UserRegistrationDto {

    @NotEmpty(message = "Укажите преподаваемые дисциплины")
    private Set<Discipline> disciplines;

}
