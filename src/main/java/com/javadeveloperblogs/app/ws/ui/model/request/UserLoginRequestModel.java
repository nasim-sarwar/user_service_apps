package com.javadeveloperblogs.app.ws.ui.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * REST request model for user authentication/login operations.
 *
 * This class serves as a Data Transfer Object (DTO) for capturing user credentials
 * submitted during the login process. It is typically used in authentication endpoints
 * where users provide their email and password to obtain access tokens or establish
 * authenticated sessions.
 *
 * Fields:
 * - email: The user's email address used as their username/identifier
 * - password: The user's plain-text password (will be encrypted/hashed during authentication)
 *
 * Security Considerations:
 * - Passwords should NEVER be logged or stored in plain text
 * - This model should only be used for receiving credentials over HTTPS
 * - Password field should be cleared from memory after authentication
 * - Consider rate limiting on login endpoints to prevent brute force attacks
 * - Implement account lockout mechanisms after repeated failed attempts
 *
 * Authentication Flow:
 * 1. Client submits email and password via POST to /login or /authenticate endpoint
 * 2. Server validates credentials against encrypted password in database
 * 3. Upon successful authentication, server returns JWT token or creates session
 * 4. Client uses token/session for subsequent authenticated requests
 *
 * Common Usage:
 * POST /users/login
 * {
 *   "email": "user@example.com",
 *   "password": "SecurePassword123"
 * }
 *
 * Validation:
 * - Email should be validated for proper format
 * - Both fields should be required (not null/empty)
 * - Consider minimum password length requirements
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestModel {
	private String email;
	private String password;

}