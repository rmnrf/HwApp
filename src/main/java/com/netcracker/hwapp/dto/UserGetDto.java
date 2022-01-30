package com.netcracker.hwapp.dto;

import com.netcracker.hwapp.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserGetDto {
    private Long id;
    private String password;
    private String email;
    private String userType;
    private String firstName;
    private String lastName;
    private String middleName;

    public static UserGetDto mapToDto(User entity) {
        UserGetDto dto = new UserGetDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setUserType(entity.getUserType());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setMiddleName(entity.getMiddleName());
        return dto;
    }
}
