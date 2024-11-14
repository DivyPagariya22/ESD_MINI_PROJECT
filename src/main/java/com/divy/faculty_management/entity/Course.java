package com.divy.faculty_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(unique = true, nullable = false)
    private String courseCode;

    private String name;
    private String description;
    private int year;
    private String term;
    private String faculty;
    private int credits;
    private int capacity;
}
