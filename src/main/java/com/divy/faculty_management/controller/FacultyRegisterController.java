package com.divy.faculty_management.controller;

import com.divy.faculty_management.dto.FacultyRegistrationRequest;
import com.divy.faculty_management.entity.Employee;
import com.divy.faculty_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/faculty")
public class FacultyRegisterController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public Employee registerFaculty(@ModelAttribute FacultyRegistrationRequest request) throws IOException {
        return employeeService.registerFaculty(request);
    }
}
