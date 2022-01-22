package com.netcracker.hwapp.dto;

import com.netcracker.hwapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReadDTO implements DTOEntity {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
}

