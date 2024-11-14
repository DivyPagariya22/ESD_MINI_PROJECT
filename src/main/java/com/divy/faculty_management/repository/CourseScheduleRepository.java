package com.divy.faculty_management.repository;

import com.divy.faculty_management.entity.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseScheduleRepository extends JpaRepository<CourseSchedule, Long> {
    // Add any custom query methods if needed
}

