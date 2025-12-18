package com.javadeveloperblogs.app.ws.io.Repository;

import com.javadeveloperblogs.app.ws.io.entity.PasswordResetTokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository interface for PasswordResetTokenEntity data access operations.
 *
 * This repository provides CRUD operations and custom query methods for managing password
 * reset tokens in the user authentication workflow. It extends CrudRepository to inherit
 * standard data access methods for token persistence and retrieval.
 *
 * Standard Inherited Operations:
 * - save(PasswordResetTokenEntity): Create or update a password reset token
 * - findById(Long): Retrieve a token by internal database ID
 * - findAll(): Retrieve all password reset tokens
 * - delete(PasswordResetTokenEntity): Remove a token from the database
 * - count(): Get total number of active tokens
 *
 * Custom Query Methods:
 * - findByToken(String): Retrieves a password reset token record by its unique token string.
 *   This method is critical for validating tokens during the password reset process. When a user
 *   clicks the reset link in their email, this method verifies the token exists and retrieves
 *   the associated user information to allow password change.
 *
 * Usage:
 * This repository is automatically implemented by Spring Data JPA at runtime and is typically
 * used in service classes for:
 * - Storing newly generated password reset tokens
 * - Validating tokens submitted by users
 * - Retrieving user details associated with a valid token
 * - Cleaning up expired or used tokens
 *
 * Security Considerations:
 * Tokens should be deleted immediately after use to prevent reuse attacks.
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetTokenEntity, Long>{
	PasswordResetTokenEntity findByToken(String token);
}