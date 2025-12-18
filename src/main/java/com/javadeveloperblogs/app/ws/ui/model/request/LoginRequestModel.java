package com.javadeveloperblogs.app.ws.ui.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * REST request model for user authentication/login operations.
 *
 * This class serves as a Data Transfer Object (DTO) for capturing user credentials
 * submitted during the login process. It is used in authentication endpoints where
 * users provide their email and password to obtain access tokens or establish
 * authenticated sessions. This model is functionally similar to UserLoginRequestModel
 * and may be used in different authentication contexts or endpoints.
 *
 * Fields:
 * - email: The user's email address serving as their unique identifier/username
 * - password: The user's plain-text password (will be validated against encrypted
 *   password stored in the database)
 *
 * Authentication Flow:
 * 1. Client submits email and password via POST to authentication endpoint
 * 2. Server retrieves user by email from database
 * 3. Server validates plain-text password against stored encrypted password
 * 4. Upon successful authentication, server generates JWT token or creates session
 * 5. Client receives token/session for subsequent authenticated API requests
 * 6. Client includes token in Authorization header for protected endpoints
 *
 * Security Best Practices:
 * - HTTPS is mandatory to protect credentials in transit
 * - Passwords should NEVER be logged, cached, or stored in plain text
 * - Clear password from memory immediately after authentication
 * - Implement rate limiting to prevent brute force attacks (e.g., max 5 attempts per minute)
 * - Implement account lockout after repeated failed attempts (e.g., lock after 5 failures)
 * - Use constant-time comparison for password validation to prevent timing attacks
 * - Consider implementing multi-factor authentication (MFA) for enhanced security
 * - Log authentication attempts for security auditing
 *
 * Example JSON Request:
 * POST /login
 * {
 *   "email": "user@example.com",
 *   "password": "SecurePassword123!"
 * }
 *
 * Validation Requirements:
 * - Email should be validated for proper format (RFC 5322 compliant)
 * - Both fields should be required (not null or empty)
 * - Trim whitespace from email before processing
 * - Consider case-insensitive email comparison
 *
 * Error Handling:
 * - Never reveal whether email exists or password is wrong (use generic "Authentication failed")
 * - Return same error message for both invalid email and wrong password
 * - Avoid information leakage that could aid attackers
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestModel {
	private String email;
	private String password;

}