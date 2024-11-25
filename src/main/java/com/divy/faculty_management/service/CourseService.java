package com.divy.faculty_management.service;

import com.divy.faculty_management.entity.Course;
import com.divy.faculty_management.entity.CourseSchedule;
import com.divy.faculty_management.repository.CourseRepository;
import com.divy.faculty_management.repository.CourseScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseScheduleRepository courseRepository;


    public List<CourseSchedule> getAllCourses() {
        return courseRepository.findAll();
    }
}
