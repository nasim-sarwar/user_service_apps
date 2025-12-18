package com.javadeveloperblogs.app.ws.ui.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * REST request model for initiating password reset operations.
 *
 * This class serves as a Data Transfer Object (DTO) for capturing the user's email
 * address when they request a password reset. It is used in the first step of the
 * password recovery workflow, where the user indicates they've forgotten their password
 * and need to reset it.
 *
 * Field:
 * - email: The email address associated with the user's account
 *
 * Password Reset Workflow:
 * 1. User submits this request with their email to /password-reset-request endpoint
 * 2. System validates the email exists in the database
 * 3. System generates a unique, time-limited password reset token
 * 4. Token is stored in PasswordResetTokenEntity linked to the user
 * 5. Email containing reset link with token is sent to the user
 * 6. User clicks link and provides new password along with token
 * 7. System validates token and updates password
 * 8. Token is invalidated after successful reset
 *
 * Security Considerations:
 * - Never reveal whether an email exists in the system (to prevent user enumeration)
 * - Always return same success message regardless of email existence
 * - Implement rate limiting to prevent abuse (e.g., max 3 requests per hour per IP)
 * - Reset tokens should expire after a short period (e.g., 15-30 minutes)
 * - Tokens should be cryptographically secure and unpredictable
 * - Send reset emails over secure channels
 *
 * Example JSON Request:
 * POST /password-reset-request
 * {
 *   "email": "user@example.com"
 * }
 *
 * Validation:
 * - Email should be validated for proper format
 * - Email field should be required (not null/empty)
 * - Trim whitespace from email before processing
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetRequestModel {
	private String email;

}