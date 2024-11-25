package com.divy.faculty_management.helper;

import com.divy.faculty_management.entity.Course;
import com.divy.faculty_management.entity.Employee;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ProfileCard {
    private Employee employee;
    private List<Course> courses;
}
