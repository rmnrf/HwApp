package com.netcracker.hwapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "groups")
@Table(name = "groups")
@Data
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "faculties_id")
    private Faculty faculty;

    @JsonIgnore
    @OneToMany(mappedBy = "group")
    private Set<Student> students;
}
