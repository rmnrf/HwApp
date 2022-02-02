package com.netcracker.hwapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "students")
@Table(name = "students")
@Data
@NoArgsConstructor
@DiscriminatorValue("Студент")
public class Student extends User {
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
