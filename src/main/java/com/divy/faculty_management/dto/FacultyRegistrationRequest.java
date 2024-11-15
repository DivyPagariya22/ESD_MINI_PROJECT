package com.divy.faculty_management.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class FacultyRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String title;
    private String department;
    private MultipartFile photograph;  // For uploading the photograph
    private List<Long> courseIds;      // List of course IDs the faculty will teach
}
