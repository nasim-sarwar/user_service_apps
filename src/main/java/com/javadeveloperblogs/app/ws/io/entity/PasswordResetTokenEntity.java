package com.javadeveloperblogs.app.ws.io.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * JPA entity representing a password reset token for user authentication workflows.
 *
 * This entity maps to the "password_reset_tokens" table and stores temporary tokens
 * used in the password recovery process. When a user requests a password reset, a unique
 * token is generated and associated with their account, typically sent via email.
 *
 * Relationships:
 * - One-to-One with UserEntity: Each token is associated with a single user account
 *
 * Security Considerations:
 * - Tokens should be cryptographically secure and difficult to guess
 * - Tokens should have an expiration time (handled at service layer)
 * - Tokens should be single-use and invalidated after password reset
 * - Tokens should be deleted or marked as used after successful password change
 *
 * Typical workflow:
 * 1. User requests password reset
 * 2. System generates token and stores in this entity
 * 3. Token is sent to user's email
 * 4. User provides token to reset password
 * 5. System validates token and allows password change
 * 6. Token is invalidated/deleted
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Entity(name = "password_reset_tokens")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PasswordResetTokenEntity implements Serializable {

	private static final long serialVersionUID = 8051324316462829780L;

	@Id
	@GeneratedValue
	private long id;

	private String token;

	@OneToOne()
	@JoinColumn(name = "users_id")
	private UserEntity userDetails;

}