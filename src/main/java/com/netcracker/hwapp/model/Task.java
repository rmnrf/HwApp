package com.netcracker.hwapp.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

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

    @Size(min = 1, max = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "teachers_id")
    private Teacher teacher;

    @ManyToMany
    private List<Group> groups;
}