package com.javadeveloperblogs.app.ws.service;

import com.javadeveloperblogs.app.ws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * User Service Interface
 *
 * This service interface defines the contract for user management operations within the application.
 * It provides a comprehensive set of methods for user lifecycle management, including CRUD operations,
 * authentication support, email verification, and password reset functionality.
 *
 * <p><strong>Core User Management Operations:</strong></p>
 * <ul>
 *   <li><strong>createUser</strong>: Registers a new user account with encrypted password and generates unique user ID</li>
 *   <li><strong>getUser</strong>: Retrieves user information by email address (used for authentication)</li>
 *   <li><strong>getUserByUserId</strong>: Retrieves user information by public user ID</li>
 *   <li><strong>updateUser</strong>: Updates existing user profile information</li>
 *   <li><strong>deleteUser</strong>: Permanently removes a user account from the system</li>
 *   <li><strong>getUsers</strong>: Retrieves paginated list of users for administrative purposes</li>
 * </ul>
 *
 * <p><strong>Security and Verification Operations:</strong></p>
 * <ul>
 *   <li><strong>verifyEmailToken</strong>: Validates email verification token and activates user account</li>
 *   <li><strong>requestPasswordReset</strong>: Initiates password reset process and sends reset token via email</li>
 *   <li><strong>resetPassword</strong>: Completes password reset using valid token and new password</li>
 * </ul>
 *
 * <p><strong>Implementation Responsibilities:</strong></p>
 * The implementing class should:
 * <ul>
 *   <li>Handle password encryption using BCrypt or similar secure hashing algorithms</li>
 *   <li>Generate and validate secure tokens for email verification and password reset</li>
 *   <li>Enforce token expiration policies (email verification and password reset tokens)</li>
 *   <li>Implement proper transaction management for data consistency</li>
 *   <li>Validate business rules (unique email, password strength, etc.)</li>
 *   <li>Handle exception scenarios (user not found, duplicate email, invalid tokens)</li>
 *   <li>Send transactional emails for verification and password reset</li>
 *   <li>Log security-relevant events (failed verifications, password changes)</li>
 * </ul>
 *
 * <p><strong>Data Transfer Object Usage:</strong></p>
 * This service operates on {@link UserDto} objects to maintain separation between
 * the service layer and persistence layer. The DTO pattern ensures:
 * <ul>
 *   <li>Decoupling of API contracts from database entities</li>
 *   <li>Selective exposure of user information (hiding sensitive fields)</li>
 *   <li>Flexibility to modify database schema without affecting API</li>
 * </ul>
 *
 * <p><strong>Pagination Support:</strong></p>
 * The getUsers method implements pagination to handle large datasets efficiently:
 * <pre>
 * // Example: Get second page with 25 users per page
 * List&lt;UserDto&gt; users = userService.getUsers(1, 25);
 * </pre>
 *
 * <p><strong>Security Considerations:</strong></p>
 * <ul>
 *   <li>Passwords should never be stored in plain text or logged</li>
 *   <li>Email verification tokens should be cryptographically secure and single-use</li>
 *   <li>Password reset tokens should expire within a short timeframe (e.g., 1 hour)</li>
 *   <li>Failed verification/reset attempts should be rate-limited to prevent abuse</li>
 *   <li>User deletion should consider data retention policies and GDPR compliance</li>
 * </ul>
 *
 * <p><strong>Exception Handling:</strong></p>
 * Implementations should throw appropriate exceptions:
 * <ul>
 *   <li>UserServiceException for business logic violations</li>
 *   <li>DuplicateEmailException when attempting to create user with existing email</li>
 *   <li>UserNotFoundException when requested user doesn't exist</li>
 *   <li>InvalidTokenException for expired or invalid verification/reset tokens</li>
 * </ul>
 *
 * <p><strong>Usage Example:</strong></p>
 * <pre>
 * {@code @Autowired}
 * private UserService userService;
 *
 * // Create new user
 * UserDto newUser = new UserDto();
 * newUser.setEmail("user@example.com");
 * newUser.setPassword("securePassword123");
 * UserDto createdUser = userService.createUser(newUser);
 *
 * // Verify email
 * boolean verified = userService.verifyEmailToken(emailToken);
 *
 * // Request password reset
 * boolean requested = userService.requestPasswordReset("user@example.com");
 *
 * // Complete password reset
 * boolean reset = userService.resetPassword(resetToken, "newPassword456");
 * </pre>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 * @see UserDto
 * @see org.springframework.security.core.userdetails.UserDetailsService
 */
public interface UserService  extends UserDetailsService {
	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(String userId, UserDto user);
	void deleteUser(String userId);
	List<UserDto> getUsers(int page, int limit);
	boolean verifyEmailToken(String token);
	boolean requestPasswordReset(String email);
	boolean resetPassword(String token, String password);
}