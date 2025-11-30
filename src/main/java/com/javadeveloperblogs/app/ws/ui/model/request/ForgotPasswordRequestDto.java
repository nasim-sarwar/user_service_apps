package com.javadeveloperblogs.app.ws.ui.model.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
/**
 * Data Transfer Object (DTO) used when a user requests a password reset.
 * <p>
 * This DTO captures the user's email address, which will be used to send
 * a password reset link or verification code. Validation ensures the email
 * is provided and follows a valid format.
 * </p>
 *
 * <p><b>Validation Rules:</b></p>
 * <ul>
 *     <li>{@code email} — Required and must be a valid email address</li>
 * </ul>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */

public class ForgotPasswordRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    // Constructors
    public ForgotPasswordRequestDto() {
    }

    public ForgotPasswordRequestDto(String email) {
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
        return "ForgotPasswordRequestDto{" +
                "email='" + email + '\'' +
                '}';
    }
}
