package com.divy.faculty_management.repository;

import com.divy.faculty_management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Add any custom query methods if needed
}
