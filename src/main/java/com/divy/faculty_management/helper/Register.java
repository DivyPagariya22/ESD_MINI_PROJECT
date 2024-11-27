package com.divy.faculty_management.helper;

import com.divy.faculty_management.entity.Course;
import com.divy.faculty_management.entity.Employee;
import com.divy.faculty_management.entity.FacultyCourse;
import com.divy.faculty_management.repository.FacultyCourseRepository;
import com.divy.faculty_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Register {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private FacultyCourseRepository facultyCourseRepository;

    // Constructor for dependency injection
    public Register(EmployeeService employeeService, FacultyCourseRepository facultyCourseRepository) {
        this.employeeService = employeeService;
        this.facultyCourseRepository = facultyCourseRepository;
    }


    public ProfileCard getProfileCard(String authHeader) {
        // Validate token and extract email
        String email = employeeService.validateAndExtractEmail(authHeader);

        // Retrieve the employee
        Employee employee = employeeService.getEmployee(email);
        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }

        // Mask sensitive data
        employee.setPassword(null);

        // Handle photograph encoding
        String base64Image = encodePhotographToBase64(employee.getPhotographPath());
        employee.setPhotographPath(base64Image);

        // Retrieve associated courses
        List<FacultyCourse> facultyCourses = facultyCourseRepository.findByFaculty(employee);
        List<Course> courses = mapFacultyCoursesToCourses(facultyCourses);

        // Construct the ProfileCard
        ProfileCard profileCard = new ProfileCard();
        profileCard.setEmployee(employee);
        profileCard.setCourses(courses);

        return profileCard;
    }

    private String encodePhotographToBase64(String photographPath) {
        if (photographPath == null || photographPath.isEmpty()) {
            return null;
        }

        try {
            File file = new File(photographPath);
            if (file.exists()) {
                byte[] fileContent = Files.readAllBytes(file.toPath());
                return Base64.getEncoder().encodeToString(fileContent);
            } else {
                System.err.println("Photograph file not found: " + photographPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading photograph file: " + photographPath);
        }

        return null;
    }

    private List<Course> mapFacultyCoursesToCourses(List<FacultyCourse> facultyCourses) {
        return facultyCourses.stream()
                .map(FacultyCourse::getCourse)
                .collect(Collectors.toList());
    }
}
