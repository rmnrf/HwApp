package com.netcracker.hwapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "teachers")
@Table(name = "teachers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Teacher")
public class Teacher extends User {
    @ManyToMany
    private Set<Discipline> disciplines;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
//    private List<Task> tasks;
}
