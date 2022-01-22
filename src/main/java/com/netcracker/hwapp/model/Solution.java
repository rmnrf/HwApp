package com.netcracker.hwapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "solutions")
@Table(name = "solutions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Task task;

    @OneToOne
    private File file;

    private Integer grade;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
