package com.netcracker.hwapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity(name = "users")
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,
                     name = "user_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
