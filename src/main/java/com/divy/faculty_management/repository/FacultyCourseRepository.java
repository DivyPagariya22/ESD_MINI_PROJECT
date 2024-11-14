package com.divy.faculty_management.repository;

import com.divy.faculty_management.entity.FacultyCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacultyCourseRepository extends JpaRepository<FacultyCourse, Long> {
    // Add any custom query methods if needed
    @Query("SELECT fc FROM FacultyCourse fc " +
            "JOIN CourseSchedule cs ON fc.course.courseId = cs.course.courseId " +
            "WHERE fc.faculty.employeeId = :facultyId AND cs.day = :day AND cs.time = :time " +
            "AND cs.building = :building AND cs.room = :room")
    List<FacultyCourse> findByFacultyIdAndScheduleConflict(
            @Param("facultyId") Long facultyId,
            @Param("day") String day,
            @Param("time") String time,
            @Param("building") String building,
            @Param("room") String room);
}
