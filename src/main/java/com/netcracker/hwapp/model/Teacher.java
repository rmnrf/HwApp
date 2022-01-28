package com.netcracker.hwapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@DiscriminatorValue("Преподаватель")
public class Teacher extends User {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    @JsonIgnore
    private List<Task> tasks;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Discipline> disciplines;

}
