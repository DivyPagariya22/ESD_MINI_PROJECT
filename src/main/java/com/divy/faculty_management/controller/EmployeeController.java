package com.divy.faculty_management.controller;

import com.divy.faculty_management.entity.Course;
import com.divy.faculty_management.entity.Employee;
import com.divy.faculty_management.entity.FacultyCourse;
import com.divy.faculty_management.helper.ProfileCard;
import com.divy.faculty_management.repository.FacultyCourseRepository;
import com.divy.faculty_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private FacultyCourseRepository facultyCourseRepository;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        System.out.println("Received request to add employee: " + employee);
        return employeeService.saveEmployee(employee);
    }

    @PostMapping("/{employeeId}/upload-photo")
    public Employee uploadPhoto(@PathVariable Long employeeId, @RequestParam("file") MultipartFile file) throws IOException {
        return employeeService.addPhoto(employeeId, file);
    }

    @GetMapping
    public ProfileCard getEmployeeById(@RequestHeader("Authorization") String authHeader) {
        System.out.println("Received request to add employee: " + authHeader);

        String email = employeeService.validateAndExtractEmail(authHeader);
        Employee employee = employeeService.getEmployee(email);
        employee.setPassword(null);
        if (employee != null) {
            try {
                File file = new File(employee.getPhotographPath());
                byte[] fileContent = Files.readAllBytes(file.toPath());
                String base64Image = Base64.getEncoder().encodeToString(fileContent); // Using java.util.Base64
                employee.setPhotographPath(base64Image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Get the list of courses that this faculty teaches
        List<FacultyCourse> facultyCourses = facultyCourseRepository.findByFaculty(employee);

        // Convert FacultyCourse to Course
        List<Course> courses = facultyCourses.stream()
                .map(facultyCourse -> facultyCourse.getCourse())  // Get the associated Course from FacultyCourse
                .collect(Collectors.toList());

        // Log the course details (optional)
        courses.forEach(course -> System.out.println(course.getName()));


        System.out.println(employee.getEmail());
        System.out.println(courses.size());
        ProfileCard profileCard = new ProfileCard();

        profileCard.setEmployee(employee);
        profileCard.setCourses(courses);

        return profileCard;
    }
}


