package com.netcracker.hwapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO implements DTOEntity {
    @Email(message = "Email должен быть корректным адресом электронной почты")
    private String email;
    @NotBlank(message = "Необходимо указать пароль")
    private String password;
    @NotBlank(message = "Необходимо указать имя")
    private String firstName;
    @NotBlank(message = "Необходимо указать фамилию")
    private String lastName;
    @NotBlank(message = "Необходимо указать отчество")
    private String middleName;
}
