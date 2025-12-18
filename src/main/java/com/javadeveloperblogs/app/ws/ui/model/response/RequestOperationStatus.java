package com.javadeveloperblogs.app.ws.ui.model.response;

/**
 * Enumeration representing the operational status of API requests.
 *
 * This enum provides a standardized way to communicate the outcome of API operations
 * to clients, indicating whether a request was processed successfully or encountered
 * an error. It is typically used in operation response models to provide consistent
 * status reporting across all API endpoints.
 *
 * Values:
 * - ERROR: Indicates the operation failed due to validation errors, business rule
 *   violations, system errors, or other issues that prevented successful completion
 * - SUCCESS: Indicates the operation completed successfully and the requested action
 *   was performed (e.g., resource created, updated, or deleted)
 *
 * Common Usage Scenarios:
 * - DELETE operations: Confirming resource deletion
 * - UPDATE operations: Confirming successful modification
 * - CREATE operations: Confirming resource creation
 * - Batch operations: Reporting overall operation status
 *
 * This enum is often paired with:
 * - OperationStatusModel: Wraps status with operation name and result
 * - Error details: Additional context when status is ERROR
 * - HTTP status codes: Aligns with standard HTTP response codes
 *
 * Example Response:
 * {
 *   "operationResult": "SUCCESS",
 *   "operationName": "DELETE"
 * }
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
public enum RequestOperationStatus {
	ERROR, SUCCESS
}