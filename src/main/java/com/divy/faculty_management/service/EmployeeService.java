package com.divy.faculty_management.service;

import com.divy.faculty_management.dto.FacultyRegistrationRequest;
import com.divy.faculty_management.dto.LoginRequest;
import com.divy.faculty_management.entity.Course;
import com.divy.faculty_management.entity.Employee;
import com.divy.faculty_management.entity.FacultyCourse;
import com.divy.faculty_management.exception.GlobalExceptionHandler;
import com.divy.faculty_management.helper.EncryptionService;
import com.divy.faculty_management.helper.JWTHelper;
import com.divy.faculty_management.repository.CourseRepository;
import com.divy.faculty_management.repository.EmployeeRepository;
import com.divy.faculty_management.repository.FacultyCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.nio.file.Path;

import static java.lang.String.format;


@Service
public class EmployeeService {



    @Autowired
    private EmployeeRepository employeeRepository;

    private final String photoDirectory = "/home/divy/IdeaProjects/faculty_management/uploads/photos/";

    @Autowired
    private FacultyCourseRepository facultyCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private JWTHelper jwtHelper;


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
    public String registerFaculty(FacultyRegistrationRequest request) throws IOException {
        // Create new employee object
        Employee employee = new Employee();
        employee.setEmail(request.email());

        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + employee.getEmail());
        }

        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());

        employee.setTitle(request.title());
        employee.setDepartment(request.department());
        employee.setPassword(encryptionService.encode(request.password()));


        MultipartFile photograph = request.photograph();

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
        List<Long> courseIds = request.courseIds();
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

        return savedEmployee.getEmail();
    }



    public String login(LoginRequest request) throws Exception {

        Employee employee = getEmployee(request.email());
        if(employee.getEmail() == null) {
            throw new Exception("Wrong email");
        }
        if(!encryptionService.validates(request.password(), employee.getPassword())) {
            throw new Exception("Wrong password");
        }

        return jwtHelper.generateToken(request.email());

    }

    public Employee getEmployee(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ExpressionException(
                        format("Cannot Find Customer:: No Employee found:: %s", email)
                ));
    }


    public String validateAndExtractEmail(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid Authorization header");
        }
        String token = authHeader.substring(7);

        if (!jwtHelper.isTokenValid(token)) {
            throw new RuntimeException("Invalid or expired token");
        }
        return jwtHelper.extractEmail(token);
    }
}

