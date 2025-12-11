package com.javadeveloperblogs.app.ws.ui.model.request;

import jakarta.validation.constraints.NotNull;
/**
 * Data Transfer Object (DTO) used to update the status of a user account.
 * <p>
 * This DTO allows activating or deactivating a user and optionally providing a reason
 * for the status change.
 * </p>
 *
 * <p><b>Validation Rules:</b></p>
 * <ul>
 *     <li>{@code active} — Required field indicating whether the user account is active or inactive</li>
 *     <li>{@code reason} — Optional explanation for status change</li>
 * </ul>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
public class UserStatusRequestDto {
    @NotNull(message = "Status is required")
    private Boolean active;

    private String reason;

    // Constructors
    public UserStatusRequestDto() {
    }

    public UserStatusRequestDto(Boolean active, String reason) {
        this.active = active;
        this.reason = reason;
    }

    // Getters and Setters
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "UserStatusRequestDto{" +
                "active=" + active +
                ", reason='" + reason + '\'' +
                '}';
    }
}
