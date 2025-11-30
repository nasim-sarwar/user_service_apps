package com.javadeveloperblogs.app.ws.ui.model.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
/**
 * Data Transfer Object (DTO) used for handling user login requests.
 * <p>
 * This DTO contains the credentials required for authentication,
 * including the user's email and password. Validation ensures that
 * both fields are provided and formatted correctly.
 * </p>
 *
 * <p><b>Validation Rules:</b></p>
 * <ul>
 *     <li>{@code email} — Required and must be a valid email address</li>
 *     <li>{@code password} — Required</li>
 * </ul>
 *
 * <p>
 * For security reasons, the password value is masked in the {@code toString()}
 * method to prevent sensitive information from being exposed in logs.
 * </p>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}