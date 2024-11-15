package com.divy.faculty_management.service;


import com.divy.faculty_management.entity.Course;
import com.divy.faculty_management.entity.Employee;
import com.divy.faculty_management.entity.FacultyCourse;
import com.divy.faculty_management.repository.CourseRepository;
import com.divy.faculty_management.repository.EmployeeRepository;
import com.divy.faculty_management.repository.FacultyCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CourseScheduleService {

    @Autowired
    private FacultyCourseRepository facultyCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Assigns a course to a faculty member.
     * The conflict logic will now be handled on the frontend.
     *
     * @param facultyId The ID of the faculty member.
     * @param courseId The ID of the course to assign.
     * @return Message indicating success or failure.
     */
    public String assignCourseToFaculty(Long facultyId, Long courseId) {
        Employee faculty = employeeRepository.findById(facultyId)
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found with ID: " + facultyId));

        // Fetch the course by its ID
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));

        // Create a new FacultyCourse assignment
        FacultyCourse facultyCourse = new FacultyCourse();
        facultyCourse.setFaculty(faculty); // Set the faculty (Employee)
        facultyCourse.setCourse(course);   // Set the course

        // Save the course assignment to the repository
        facultyCourseRepository.save(facultyCourse);

        return "Course successfully assigned to faculty.";
    }
}

