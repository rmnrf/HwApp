package com.netcracker.hwapp.model;

import com.netcracker.hwapp.enums.Role;
import com.netcracker.hwapp.enums.Status;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "developers")
@Data
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
