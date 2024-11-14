package com.divy.faculty_management.repository;

import com.divy.faculty_management.entity.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CourseScheduleRepository extends JpaRepository<CourseSchedule, Long> {
    // Add any custom query methods if needed
    @Query("SELECT cs FROM CourseSchedule cs WHERE cs.course.courseId = :courseId")
    Optional<CourseSchedule> findByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT cs FROM CourseSchedule cs WHERE cs.day = :day AND cs.time = :time AND cs.building = :building AND cs.room = :room AND cs.course.courseId != :courseId")
    List<CourseSchedule> findConflictingSchedules(
            @Param("day") String day,
            @Param("time") String time,
            @Param("building") String building,
            @Param("room") String room,
            @Param("courseId") Long courseId
    );
}

