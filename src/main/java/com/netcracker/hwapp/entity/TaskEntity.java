package com.netcracker.hwapp.entity;

import com.netcracker.hwapp.model.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    private LocalDate creationDate;

    private LocalDate deadlineDate;
    private String description;

    private Boolean expired = false;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
}
