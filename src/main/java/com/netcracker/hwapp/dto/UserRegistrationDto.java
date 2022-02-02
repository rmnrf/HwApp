package com.netcracker.hwapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserRegistrationDto {

    @Email(message = "Email должен быть корректным адресом электронной почты")
    @NotBlank(message = "Необходимо указать email")
    private String email;

    @Size(min = 8, max = 50, message = "Длина пароля должна находиться в диапазоне от 8 до 50 символов")
    @NotBlank(message = "Необходимо указать пароль")
    private String password;

    @Size(min = 1, max = 30, message = "Длина имени должна находиться в диапазоне от 1 до 30 символов")
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ]+$", message = "Допустимы только русские и латинские символы")
    @NotBlank(message = "Необходимо указать имя")
    private String firstName;

    @Size(min = 1, max = 30, message = "Длина фамилии должна находиться в диапазоне от 1 до 30 символов")
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ]+$", message = "Допустимы только русские и латинские символы")
    @NotBlank(message = "Необходимо указать фамилию")
    private String lastName;

    @Size(min = 1, max = 30, message = "Длина отчества должна находиться в диапазоне от 1 до 30 символов")
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ]+$", message = "Допустимы только русские и латинские символы")
    @NotBlank(message = "Необходимо указать отчество")
    private String middleName;

    private String role;

}
