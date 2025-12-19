package com.javadeveloperblogs.app.ws.exception;

/**
 * Custom runtime exception for handling user service-related errors.
 *
 * <p>This exception is thrown when business logic violations or error conditions
 * occur within the user service layer. By extending {@link RuntimeException}, this
 * exception is unchecked, meaning it does not need to be explicitly declared in
 * method signatures or caught by calling code.</p>
 *
 * <p>Common scenarios where this exception is thrown include:</p>
 * <ul>
 *   <li>Attempting to create a user with an email that already exists</li>
 *   <li>User not found when performing update or delete operations</li>
 *   <li>Invalid user data that violates business rules</li>
 *   <li>Authentication or authorization failures specific to user operations</li>
 * </ul>
 *
 * <p>This exception should be caught at the controller or global exception handler
 * level to provide appropriate HTTP responses to API clients.</p>
 *
 * <p><strong>Example usage:</strong></p>
 * <pre>
 * {@code
 * if (userRepository.findByEmail(email) != null) {
 *     throw new UserServiceException("User with this email already exists");
 * }
 * }
 * </pre>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 * @see RuntimeException
 */
public class UserServiceException extends RuntimeException {

	/**
	 * Serial version UID for serialization compatibility.
	 *
	 * <p>This ensures that serialized instances of this exception can be
	 * deserialized correctly even if the class definition changes in future
	 * versions (as long as the serialVersionUID remains the same).</p>
	 */
	private static final long serialVersionUID = 1348771109171435607L;

	/**
	 * Constructs a new UserServiceException with the specified error message.
	 *
	 * <p>The error message should be descriptive and provide clear information
	 * about what went wrong. This message will be propagated up the call stack
	 * and can be logged or returned to the client in an error response.</p>
	 *
	 * @param message a descriptive error message explaining the reason for the exception.
	 *                Should not be null or empty for meaningful error reporting.
	 *
	 * @throws NullPointerException if message is null (inherited from parent constructor)
	 *
	 * @see RuntimeException#RuntimeException(String)
	 */
	public UserServiceException(String message) {
		super(message);
	}
}