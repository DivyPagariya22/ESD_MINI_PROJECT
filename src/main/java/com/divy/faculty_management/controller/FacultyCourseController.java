package com.divy.faculty_management.controller;

import com.divy.faculty_management.entity.FacultyCourse;
import com.divy.faculty_management.entity.CourseSchedule;
import com.divy.faculty_management.entity.Course;
import com.divy.faculty_management.entity.Employee;
import com.divy.faculty_management.service.CourseService;
import com.divy.faculty_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faculty-courses")
public class FacultyCourseController {

    @Autowired
    private CourseService facultyCourseService;

    @Autowired
    private EmployeeService employeeService; // Service to fetch faculty details

    @Autowired
    private CourseService courseService; // Service to fetch course details

    @Autowired
    private CourseScheduleService courseScheduleService; // Service to fetch course schedule details

    @PostMapping("/assign")
    public String assignCourseToFaculty(@RequestParam Long facultyId,
                                        @RequestParam Long courseId,
                                        @RequestParam Long courseScheduleId) {

        // Fetch faculty, course, and course schedule from the database
        Employee faculty = employeeService.getEmployeeById(facultyId);
        Course course = courseService.getCourseById(courseId);
        CourseSchedule courseSchedule = courseScheduleService.getCourseScheduleById(courseScheduleId);

        // Retrieve course schedule details (day, time, building, room)
        String day = courseSchedule.getDay();
        String time = courseSchedule.getTime();
        String building = courseSchedule.getBuilding();
        String room = courseSchedule.getRoom();

        // Check if there's already a schedule conflict for the same faculty
        boolean hasConflict = facultyCourseService.checkScheduleConflict(facultyId, day, time, building, room);

        if (hasConflict) {
            return "Schedule conflict: The faculty is already assigned to another course at this time.";
        }

        // If no conflict, assign the course to the faculty
        FacultyCourse facultyCourse = new FacultyCourse();
        facultyCourse.setFaculty(faculty);
        facultyCourse.setCourse(course);
        facultyCourse.setCourseSchedule(courseSchedule);

        // Save the facultyCourse to the database
        facultyCourseService.saveFacultyCourse(facultyCourse);

        return "Course successfully assigned to faculty.";
    }
}
