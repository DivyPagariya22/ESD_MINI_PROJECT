package com.divy.faculty_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CourseSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private String day;    // e.g., "Monday"

    private String time;   // e.g., "10:00 AM - 11:30 AM"

    private String building;

    private String room;
}


