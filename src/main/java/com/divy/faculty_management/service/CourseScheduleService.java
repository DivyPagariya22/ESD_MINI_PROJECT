package com.divy.faculty_management.service;


import com.divy.faculty_management.entity.CourseSchedule;
import com.divy.faculty_management.repository.CourseScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseScheduleService {

    @Autowired
    private CourseScheduleRepository courseScheduleRepository;

    // Method to get CourseSchedule by its ID
    public CourseSchedule getCourseScheduleById(Long courseScheduleId) {
        return courseScheduleRepository.findById(courseScheduleId).orElse(null);
    }
}

