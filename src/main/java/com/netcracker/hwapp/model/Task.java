package com.netcracker.hwapp.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity(name = "tasks")
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    private LocalDateTime deadlineDateTime;

    @ManyToOne
    @JoinColumn(name = "disciplines_id")
    private Discipline discipline;

    private String description;

    //private Boolean expired = false;

    @ManyToOne
    @JoinColumn(name = "teachers_id")
    private Teacher teacher;

    @ManyToMany
    private List<Group> groups;
}