package com.divy.faculty_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record FacultyRegistrationRequest(
        @NotNull(message = "First name is required")
        @NotEmpty(message = "First name cannot be empty")
        @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
        String firstName,

        @NotNull(message = "Last name is required")
        @NotEmpty(message = "Last name cannot be empty")
        @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
        String lastName,

        @NotNull(message = "Email is required")
        @NotEmpty(message = "Email cannot be empty")
        @Email(message = "Email must be in a valid format")
        String email,

        @NotNull(message = "Title is required")
        @NotEmpty(message = "Title cannot be empty")
        String title,

        @NotNull(message = "Department is required")
        @NotEmpty(message = "Department cannot be empty")
        String department,

        @NotNull(message = "Password is required")
        @NotEmpty(message = "Password cannot be empty")
        @Size(min = 6, max = 12, message = "Password must be between 8 and 16 characters")
        String password,

        @NotNull(message = "Photograph is required")
        MultipartFile photograph, // `@NotNull` ensures the file is provided

        @NotNull(message = "Course IDs cannot be null")
        List<Long> courseIds
) {}
