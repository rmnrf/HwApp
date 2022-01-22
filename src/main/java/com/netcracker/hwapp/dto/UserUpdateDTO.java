package com.netcracker.hwapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO implements DTOEntity {
    @NotBlank(message = "Необходимо указать пароль")
    private String password;
    @NotBlank(message = "Необходимо указать имя")
    private String firstName;
    @NotBlank(message = "Необходимо указать фамилию")
    private String lastName;
    @NotBlank(message = "Необходимо указать отчество")
    private String middleName;
}
