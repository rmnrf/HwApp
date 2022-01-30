package com.netcracker.hwapp.dto;

import com.netcracker.hwapp.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserUpdateDto {

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

    public static UserUpdateDto mapToDto(User entity) {
        UserUpdateDto dto = new UserUpdateDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setMiddleName(entity.getMiddleName());
        return dto;
    }
}
