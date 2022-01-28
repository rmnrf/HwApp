package com.netcracker.hwapp.dto;

import com.netcracker.hwapp.model.Faculty;
import com.netcracker.hwapp.model.Group;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class StudentRegistrationDto extends UserRegistrationDto {

    @NotEmpty(message = "Укажите номер группы")
    private Group group;

    @NotEmpty(message = "Укажите факультет")
    private Faculty faculty;
}
