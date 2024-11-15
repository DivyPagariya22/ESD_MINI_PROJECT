package com.divy.faculty_management.service;

import com.divy.faculty_management.dto.FacultyRegistrationRequest;
import com.divy.faculty_management.entity.Course;
import com.divy.faculty_management.entity.Employee;
import com.divy.faculty_management.entity.FacultyCourse;
import com.divy.faculty_management.repository.CourseRepository;
import com.divy.faculty_management.repository.EmployeeRepository;
import com.divy.faculty_management.repository.FacultyCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.nio.file.Path;


@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final String photoDirectory = "/home/divy/IdeaProjects/faculty_management/uploads/photos/";

    @Autowired
    private FacultyCourseRepository facultyCourseRepository;

    @Autowired
    private CourseRepository courseRepository;


    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Old Method

    public Employee addPhoto(Long employeeId, MultipartFile file) throws IOException {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            Path path = Paths.get(photoDirectory);


            if (!Files.exists(path)) {
                Files.createDirectories(path);  // Create the directory if it doesn't exist
            }

            // Generate the file path
            String fileName =  employeeId + "_" + file.getOriginalFilename();
            String filePath = photoDirectory + fileName;

            // Destination file
            File dest = new File(filePath);

            // Transfer file to the destination directory
            file.transferTo(dest);

            employee.setPhotographPath(filePath);
            return employeeRepository.save(employee);
        }
        return null;
    }


    // New Method
    public Employee registerFaculty(FacultyRegistrationRequest request) throws IOException {
        // Create new employee object
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setTitle(request.getTitle());
        employee.setDepartment(request.getDepartment());


        MultipartFile photograph = request.getPhotograph();

        Path path = Paths.get(photoDirectory);
        if (!Files.exists(path)) {
            Files.createDirectories(path);  // Create the directory if it doesn't exist
        }

        // Generate the file name and path
        String fileName = employee.getFirstName() + "_" + employee.getLastName() + "_" + photograph.getOriginalFilename();
        String filePath = photoDirectory + fileName;

        // Destination file
        File dest = new File(filePath);

        // Transfer the file to the destination directory
        photograph.transferTo(dest);

        System.out.println("---------File Path-------------- => " + dest.getAbsolutePath());



        employee.setPhotographPath(filePath);

        // Save employee to database
        Employee savedEmployee = employeeRepository.save(employee);

        // Assign selected courses to faculty
        List<Long> courseIds = request.getCourseIds();
        if (courseIds != null) {
            for (Long courseId : courseIds) {
                Optional<Course> courseOptional = courseRepository.findById(courseId);
                courseOptional.ifPresent(course -> {
                    FacultyCourse facultyCourse = new FacultyCourse();
                    facultyCourse.setFaculty(savedEmployee);
                    facultyCourse.setCourse(course);
                    facultyCourseRepository.save(facultyCourse);
                });
            }
        }

        return savedEmployee;
    }


}

