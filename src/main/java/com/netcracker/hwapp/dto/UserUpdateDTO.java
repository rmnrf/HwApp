package com.netcracker.hwapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.netcracker.hwapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO implements DTOEntity {
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
}
