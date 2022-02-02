package com.netcracker.hwapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "teachers")
@Table(name = "teachers")
@Data
@NoArgsConstructor
@DiscriminatorValue("Преподаватель")
public class Teacher extends User {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    @JsonIgnore
    private List<Task> tasks;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Discipline> disciplines;
}
