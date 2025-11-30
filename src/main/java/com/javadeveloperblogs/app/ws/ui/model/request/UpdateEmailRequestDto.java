package com.javadeveloperblogs.app.ws.ui.model.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
/**
 * Data Transfer Object (DTO) used for updating a user's email address.
 * <p>
 * This DTO is typically used when an authenticated user wants to change
 * their email. It requires the new email and the current password for
 * verification.
 * </p>
 *
 * <p><b>Validation Rules:</b></p>
 * <ul>
 *     <li>{@code newEmail} — Required, must be a valid email address, max 120 characters</li>
 *     <li>{@code password} — Required for verification of identity</li>
 * </ul>
 *
 * <p>
 * The password field is masked in the {@code toString()} method to
 * prevent exposure in logs or debug output.
 * </p>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
public class UpdateEmailRequestDto {

    @NotBlank(message = "New email is required")
    @Email(message = "Email must be valid")
    @Size(max = 120, message = "Email cannot exceed 120 characters")
    private String newEmail;

    @NotBlank(message = "Password is required for email change")
    private String password;

    // Constructors
    public UpdateEmailRequestDto() {
    }

    public UpdateEmailRequestDto(String newEmail, String password) {
        this.newEmail = newEmail;
        this.password = password;
    }

    // Getters and Setters
    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UpdateEmailRequestDto{" +
                "newEmail='" + newEmail + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }
}