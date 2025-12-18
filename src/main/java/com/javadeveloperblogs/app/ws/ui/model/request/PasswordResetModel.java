package com.javadeveloperblogs.app.ws.ui.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * REST request model for completing password reset operations.
 *
 * This class serves as a Data Transfer Object (DTO) for capturing the password reset
 * token and new password during the final step of the password recovery workflow.
 * It is used when the user clicks the reset link from their email and submits their
 * new password along with the validation token.
 *
 * Fields:
 * - token: The unique password reset token sent to the user's email, used to verify
 *   the legitimacy of the reset request and identify the user account
 * - password: The new plain-text password the user wants to set (will be encrypted
 *   before storage)
 *
 * Password Reset Completion Flow:
 * 1. User receives email with reset link containing token as URL parameter
 * 2. User clicks link and is directed to password reset form
 * 3. User enters new password in the form
 * 4. Client submits this model with token and new password to /password-reset endpoint
 * 5. Server validates token exists and hasn't expired
 * 6. Server encrypts new password and updates user's credentials
 * 7. Server invalidates/deletes the used token
 * 8. User is notified of successful password change
 *
 * Security Considerations:
 * - Token should be validated for existence, expiration, and authenticity
 * - Tokens should be single-use only (deleted after successful reset)
 * - New password must meet complexity requirements (length, special characters, etc.)
 * - Password should be transmitted over HTTPS only
 * - Consider preventing reuse of recent passwords
 * - Rate limit this endpoint to prevent token brute-forcing
 * - Clear any active user sessions after password change for security
 *
 * Example JSON Request:
 * POST /password-reset
 * {
 *   "token": "a7f9e3c2-4b8d-11ec-81d3-0242ac130003",
 *   "password": "NewSecurePass123!"
 * }
 *
 * Validation:
 * - Both fields should be required (not null/empty)
 * - Token should be validated against PasswordResetTokenEntity
 * - Password should meet minimum security requirements
 * - Token expiration should be checked (typically 15-30 minutes)
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetModel {
	private String token;
	private String password;

}