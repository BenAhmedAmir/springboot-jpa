package org.example.reactive.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentDTO(
        @NotEmpty(message = "FirstName should not be empty")
        String firstname,
        @NotEmpty(message = "LastName should not be empty")
        String lastname,
        String email,
        Integer schoolId) {
}
