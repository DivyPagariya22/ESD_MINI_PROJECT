package com.divy.faculty_management.service;


import com.divy.faculty_management.entity.Course;
import com.divy.faculty_management.entity.CourseSchedule;
import com.divy.faculty_management.repository.CourseRepository;
import com.divy.faculty_management.repository.CourseScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseScheduleService {

    private CourseScheduleRepository courseScheduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    public String assignCourses(Long courseId) {
        CourseSchedule existingSchedule = courseScheduleRepository
                .findByCourseId(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course schedule not found for course ID: " + courseId));

        if (hasScheduleConflict(courseId, existingSchedule)) {
            return "Schedule conflict detected: Another course has the same day, time, building, and room.";
        }

        return "No schedule conflicts found for this course.";
    }

    private boolean hasScheduleConflict(Long courseId, CourseSchedule existingSchedule) {
        // Query for any conflicting schedules based on day, time, building, and room, excluding this course
        List<CourseSchedule> conflictingSchedules = courseScheduleRepository
                .findConflictingSchedules(
                        existingSchedule.getDay(),
                        existingSchedule.getTime(),
                        existingSchedule.getBuilding(),
                        existingSchedule.getRoom(),
                        courseId
                );

        return !conflictingSchedules.isEmpty();
    }
}

