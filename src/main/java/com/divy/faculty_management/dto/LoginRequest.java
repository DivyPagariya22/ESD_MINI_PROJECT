package com.divy.faculty_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record LoginRequest (
    @NotNull(message="Customer email is required")
    @NotEmpty(message="Email must be present")
    @Email(message = "Email must be in correct format")
    @JsonProperty("email")
    String email,

    @NotNull(message = "Password should be present")
    @NotEmpty(message = "Password should be present")
    @Size(min = 4, max = 24)
    @JsonProperty("password")
    String password
) {

}

