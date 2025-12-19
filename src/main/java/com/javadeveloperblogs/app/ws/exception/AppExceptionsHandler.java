package com.javadeveloperblogs.app.ws.exception;

import java.util.Date;

import com.javadeveloperblogs.app.ws.ui.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler for the application that provides centralized exception handling
 * across all controllers.
 *
 * <p>This class is annotated with {@link ControllerAdvice}, which makes it a global exception
 * handler that intercepts exceptions thrown from any controller in the application. It provides
 * consistent error response formatting and appropriate HTTP status codes for different types
 * of exceptions.</p>
 *
 * <p>Key responsibilities:</p>
 * <ul>
 *   <li>Catching and handling application-specific exceptions (UserServiceException)</li>
 *   <li>Catching and handling generic/unexpected exceptions</li>
 *   <li>Converting exceptions into standardized ErrorMessage responses</li>
 *   <li>Setting appropriate HTTP status codes for different error scenarios</li>
 *   <li>Preventing sensitive stack traces from being exposed to clients</li>
 * </ul>
 *
 * <p>All exceptions are converted into a consistent {@link ErrorMessage} format containing
 * a timestamp and error description, which provides a uniform API error response structure
 * for clients.</p>
 *
 * <p><strong>Error Response Format:</strong></p>
 * <pre>
 * {
 *   "timestamp": "2024-12-18T10:30:00.000+00:00",
 *   "message": "Error description here"
 * }
 * </pre>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 * @see ControllerAdvice
 * @see ExceptionHandler
 * @see UserServiceException
 * @see ErrorMessage
 */
@ControllerAdvice
public class AppExceptionsHandler {

	/**
	 * Handles UserServiceException thrown from any controller or service layer.
	 *
	 * <p>This method is invoked automatically by Spring when a {@link UserServiceException}
	 * is thrown anywhere in the application. It creates a standardized error response with
	 * the exception message and returns it with an HTTP 500 Internal Server Error status.</p>
	 *
	 * <p>Note: Currently returns HTTP 500 for all UserServiceException instances. Consider
	 * refactoring to return more specific status codes based on the error type (e.g., 400
	 * for validation errors, 404 for not found, 409 for conflicts like duplicate emails).</p>
	 *
	 * <p><strong>Example scenarios:</strong></p>
	 * <ul>
	 *   <li>User tries to register with an existing email → "Record already exists"</li>
	 *   <li>Attempting to update a non-existent user</li>
	 *   <li>Business rule violations in user operations</li>
	 * </ul>
	 *
	 * @param ex the UserServiceException that was thrown, containing the error message
	 * @param request the current web request, providing context about the HTTP request
	 *                (useful for logging or including request details in the response)
	 * @return a ResponseEntity containing the ErrorMessage body, empty headers, and
	 *         HTTP 500 status code
	 *
	 * @see UserServiceException
	 * @see ErrorMessage
	 */
	@ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());

		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handles all other uncaught exceptions that are not specifically handled by other methods.
	 *
	 * <p>This method serves as a catch-all exception handler for any exception type that
	 * doesn't have a more specific handler. It prevents unhandled exceptions from propagating
	 * to the client and exposing sensitive information like stack traces or internal system
	 * details.</p>
	 *
	 * <p>This handler catches exceptions such as:</p>
	 * <ul>
	 *   <li>NullPointerException - unexpected null values</li>
	 *   <li>IllegalArgumentException - invalid method arguments</li>
	 *   <li>Database exceptions - connection failures, constraint violations</li>
	 *   <li>Any other RuntimeException or checked Exception not handled elsewhere</li>
	 * </ul>
	 *
	 * <p><strong>Best Practice Note:</strong> While returning HTTP 500 for all unexpected
	 * exceptions is safe, consider logging the full exception details (including stack trace)
	 * for debugging purposes while only exposing a generic error message to clients to
	 * prevent information leakage.</p>
	 *
	 * @param ex the generic Exception that was thrown, containing the error details
	 * @param request the current web request, providing context about the HTTP request
	 *                that triggered the exception
	 * @return a ResponseEntity containing the ErrorMessage body, empty headers, and
	 *         HTTP 500 status code
	 *
	 * @see Exception
	 * @see ErrorMessage
	 */
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());

		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}