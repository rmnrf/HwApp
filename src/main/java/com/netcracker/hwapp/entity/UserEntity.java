//package com.netcracker.hwapp.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import java.util.List;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class UserEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Email(message = "Email должен быть корректным адресом электронной почты")
//    private String email;
//    @NotBlank(message = "Необходимо указать пароль")
//    private String password;
//    @NotBlank(message = "Необходимо указать имя")
//    private String firstName;
//    @NotBlank(message = "Необходимо указать фамилию")
//    private String lastName;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<TodoEntity> todos;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator")
//    private List<TaskEntity> tasks;
//}
