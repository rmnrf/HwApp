package com.netcracker.hwapp.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "tasks")
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    private LocalDate creationDate;

    private LocalDate deadlineDate;

    @ManyToOne
    @JoinColumn(name = "disciplines_id")
    private Discipline discipline;

    private String description;

    //private Boolean expired = false;

    @ManyToOne
    @JoinColumn(name = "teachers_id")
    private Teacher teacher;

    @OneToMany
    private Set<Group> groups;
}