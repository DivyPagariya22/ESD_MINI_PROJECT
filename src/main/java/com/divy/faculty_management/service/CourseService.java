package com.divy.faculty_management.service;

import com.divy.faculty_management.entity.FacultyCourse;
import com.divy.faculty_management.repository.FacultyCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private FacultyCourseRepository facultyCourseRepository;

    public boolean checkScheduleConflict(Long facultyId, String day, String time, String building, String room) {
        // Query the repository to find if there's already a course assigned to the faculty
        List<FacultyCourse> existingCourses = facultyCourseRepository.findByFacultyIdAndScheduleConflict(facultyId, day, time, building, room);

        // If the list is empty, there's no conflict
        return !existingCourses.isEmpty();
    }

    public FacultyCourse saveFacultyCourse(FacultyCourse facultyCourse) {
        return facultyCourseRepository.save(facultyCourse);
    }
}
