package com.netcracker.hwapp.model;

import com.netcracker.hwapp.entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator")
//    private List<TaskEntity> tasks;
}
