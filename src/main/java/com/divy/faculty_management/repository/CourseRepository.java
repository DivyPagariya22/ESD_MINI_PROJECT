package com.divy.faculty_management.repository;

import com.divy.faculty_management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // Add any custom query methods if needed
}
