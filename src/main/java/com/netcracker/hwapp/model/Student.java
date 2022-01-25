package com.netcracker.hwapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "students")
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Student")
public class Student extends User {
    @NotNull
    @ManyToOne
    private Group group;
}
