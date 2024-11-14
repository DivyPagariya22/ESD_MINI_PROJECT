package com.divy.faculty_management.controller;

import com.divy.faculty_management.entity.Employee;
import com.divy.faculty_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PostMapping("/{employeeId}/upload-photo")
    public Employee uploadPhoto(@PathVariable Long employeeId, @RequestParam("file") MultipartFile file) throws IOException {
        return employeeService.addPhoto(employeeId, file);
    }
}

