package com.netcracker.hwapp.dto;

import com.netcracker.hwapp.model.Faculty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StudentRegistrationDto extends UserRegistrationDto {

    private Integer groupNumber;

    @NotNull(message = "Укажите факультет")
    private Faculty faculty;
}
