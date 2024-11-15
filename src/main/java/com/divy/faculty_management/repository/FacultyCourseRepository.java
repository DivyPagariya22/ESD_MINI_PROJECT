package com.divy.faculty_management.repository;

import com.divy.faculty_management.entity.FacultyCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacultyCourseRepository extends JpaRepository<FacultyCourse, Long> {
    // Add any custom query methods if needed

}
