package com.javadeveloperblogs.app.ws.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * REST response model for API error responses.
 *
 * This class provides a standardized structure for communicating error information
 * to API clients when requests fail. It is typically returned by exception handlers
 * in the controller advice layer, ensuring consistent error response formatting
 * across all API endpoints.
 *
 * Fields:
 * - timestamp: The exact date and time when the error occurred, helping with
 *   debugging, log correlation, and troubleshooting
 * - message: A human-readable description of the error, typically sourced from
 *   the ErrorMessages enum or custom exception messages
 *
 * Common Usage Scenarios:
 * - Validation failures (400 Bad Request)
 * - Resource not found errors (404 Not Found)
 * - Authentication failures (401 Unauthorized)
 * - Authorization failures (403 Forbidden)
 * - Internal server errors (500 Internal Server Error)
 * - Conflict errors (409 Conflict)
 *
 * Example JSON Response:
 * {
 *   "timestamp": "2025-01-15T10:30:45.123Z",
 *   "message": "Record with provided id is not found"
 * }
 *
 * Integration with Exception Handling:
 * This model is typically populated in @ControllerAdvice or @ExceptionHandler
 * methods to provide consistent error responses regardless of which layer
 * (controller, service, repository) throws the exception.
 *
 * Design Considerations:
 * - Keep messages user-friendly and avoid exposing system internals
 * - Timestamp aids in correlating errors with server logs
 * - Can be extended with additional fields (errorCode, path, details) if needed
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
	private Date timestamp;
	private String message;

}