package com.netcracker.hwapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;

    @Size(min = 8, max = 50, message = "Длина пароля должна находиться в диапазоне от 8 до 50 символов")
    @NotBlank(message = "Необходимо указать пароль")
    private String password;
}
