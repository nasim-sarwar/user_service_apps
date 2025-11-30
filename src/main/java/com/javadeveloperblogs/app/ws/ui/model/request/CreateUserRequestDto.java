package com.javadeveloperblogs.app.ws.ui.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) used for creating a new user account.
 * <p>
 * This DTO is used during the user registration process and contains
 * user details such as first name, last name, email, and password.
 * Validation annotations ensure that all required fields are provided
 * and meet the application's formatting and size constraints.
 * </p>
 *
 * <p><b>Validation Rules:</b></p>
 * <ul>
 *     <li>{@code firstName} — Required, maximum 50 characters</li>
 *     <li>{@code lastName} — Required, maximum 50 characters</li>
 *     <li>{@code email} — Required, must be a valid email address, maximum 120 characters</li>
 *     <li>{@code password} — Required, between 8 and 100 characters</li>
 * </ul>
 *
 * <p>
 * The password field is masked in the {@code toString()} method
 * to protect sensitive information from appearing in logs.
 * </p>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 120, message = "Email cannot exceed 120 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    // Optional: List of addresses during registration
    private List<AddressRequestDto> addresses;
}
