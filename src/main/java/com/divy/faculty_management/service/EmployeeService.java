package com.divy.faculty_management.service;

import com.divy.faculty_management.entity.Employee;
import com.divy.faculty_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final String photoDirectory = "uploads/photos/";

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee addPhoto(Long employeeId, MultipartFile file) throws IOException {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            String filePath = photoDirectory + employeeId + "_" + file.getOriginalFilename();
            File dest = new File(filePath);
            file.transferTo(dest);

            employee.setPhotographPath(filePath);
            return employeeRepository.save(employee);
        }
        return null;
    }
}

