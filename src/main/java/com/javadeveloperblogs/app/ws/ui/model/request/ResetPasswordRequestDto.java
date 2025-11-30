package com.javadeveloperblogs.app.ws.ui.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
/**
 * Data Transfer Object (DTO) used for resetting a user's password.
 * <p>
 * This DTO is typically received after the user clicks a password reset link
 * sent to their email. It contains the reset token along with the new password
 * and a confirmation field to ensure accuracy.
 * </p>
 *
 * <p><b>Validation Rules:</b></p>
 * <ul>
 *     <li>{@code token} — Required and used to validate the reset request</li>
 *     <li>{@code newPassword} — Required; must be between 8 and 100 characters</li>
 *     <li>{@code confirmPassword} — Required; should match the new password</li>
 * </ul>
 *
 * <p>
 * Sensitive fields such as passwords and the reset token are masked in
 * {@code toString()} to prevent accidental exposure in logs.
 * </p>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */

public class ResetPasswordRequestDto {
    @NotBlank(message = "Reset token is required")
    private String token;

    @NotBlank(message = "New password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String newPassword;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

    // Constructors
    public ResetPasswordRequestDto() {
    }

    public ResetPasswordRequestDto(String token, String newPassword, String confirmPassword) {
        this.token = token;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "ResetPasswordRequestDto{" +
                "token='[REDACTED]'" +
                ", newPassword='[PROTECTED]'" +
                ", confirmPassword='[PROTECTED]'" +
                '}';
    }
}
