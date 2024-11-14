package com.divy.faculty_management.controller;

import com.divy.faculty_management.service.CourseScheduleService;
import com.divy.faculty_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faculty-courses")
public class FacultyCourseController {

    @Autowired
    private EmployeeService employeeService; // Service to fetch faculty details

    @Autowired
    private CourseScheduleService courseScheduleService; // Service to fetch course schedule details

    @PostMapping("/assign")
    public String assignCourses(@RequestBody Long courseId) {
        return courseScheduleService.assignCourses(courseId);
    }
}
