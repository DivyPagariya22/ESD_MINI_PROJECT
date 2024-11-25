package com.divy.faculty_management.controller;

import com.divy.faculty_management.entity.Course;
import com.divy.faculty_management.entity.CourseSchedule;
import com.divy.faculty_management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseSchedule>> getAllCourses() {
        List<CourseSchedule> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
}