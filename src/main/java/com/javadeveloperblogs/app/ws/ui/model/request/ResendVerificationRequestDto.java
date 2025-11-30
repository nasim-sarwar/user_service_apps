package com.javadeveloperblogs.app.ws.ui.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
/**
 * Data Transfer Object (DTO) used for requesting a new verification email.
 * <p>
 * This DTO contains the user's email address, which is validated to ensure
 * a proper format before sending a new account verification link.
 * </p>
 *
 * <p><b>Validation Rules:</b></p>
 * <ul>
 *     <li>{@code email} — Required and must be a valid email address</li>
 * </ul>
 *
 * <p>
 * The {@code toString()} method displays the email but should still be used
 * cautiously in logs to avoid unnecessary exposure of user information.
 * </p>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */

public class ResendVerificationRequestDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    // Constructors
    public ResendVerificationRequestDto() {
    }

    public ResendVerificationRequestDto(String email) {
        this.email = email;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ResendVerificationRequestDto{" +
                "email='" + email + '\'' +
                '}';
    }
}
