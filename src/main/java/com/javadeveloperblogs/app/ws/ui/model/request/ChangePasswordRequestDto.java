package com.javadeveloperblogs.app.ws.ui.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
/**
 * Data Transfer Object (DTO) used for handling change password requests.
 * <p>
 * This DTO is used when an authenticated user attempts to update their
 * account password. It includes the current password for verification,
 * the new desired password, and a confirmation field to ensure that the
 * new password is entered correctly.
 * </p>
 *
 * <p><b>Validation Rules:</b></p>
 * <ul>
 *     <li>{@code currentPassword} — Must not be blank</li>
 *     <li>{@code newPassword} — Must not be blank and must be between 8 and 100 characters</li>
 *     <li>{@code confirmPassword} — Must not be blank</li>
 * </ul>
 *
 * <p>
 * Sensitive fields such as passwords are masked in {@code toString()} to
 * prevent accidental exposure in logs.
 * </p>
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2024
 */

public class ChangePasswordRequestDto {
    @NotBlank(message = "Current password is required")
    private String currentPassword;

    @NotBlank(message = "New password is required")
    @Size(min = 8, max = 100, message = "New password must be between 8 and 100 characters")
    private String newPassword;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

    // Constructors
    public ChangePasswordRequestDto() {
    }

    public ChangePasswordRequestDto(String currentPassword, String newPassword, String confirmPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    // Getters and Setters
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
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
        return "ChangePasswordRequestDto{" +
                "currentPassword='[PROTECTED]'" +
                ", newPassword='[PROTECTED]'" +
                ", confirmPassword='[PROTECTED]'" +
                '}';
    }
}
