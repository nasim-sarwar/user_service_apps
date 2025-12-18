package com.javadeveloperblogs.app.ws.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * REST response model for reporting the status of API operations.
 *
 * This class provides a standardized structure for communicating operation outcomes
 * to API clients. It is primarily used for operations that don't return resource data,
 * such as DELETE requests, or for operations where the client needs explicit confirmation
 * of the action performed.
 *
 * Fields:
 * - operationResult: The outcome status, typically "SUCCESS" or "ERROR"
 *   (corresponds to RequestOperationStatus enum values)
 * - operationName: The type of operation performed (e.g., "DELETE", "UPDATE", "VERIFY")
 *
 * Common Use Cases:
 * - DELETE endpoints: Confirming resource deletion without returning the deleted resource
 * - Verification operations: Email verification, password reset confirmation
 * - Batch operations: Reporting status of bulk actions
 * - State-changing operations: Account activation, deactivation, role assignment
 *
 * Example Response:
 * {
 *   "operationResult": "SUCCESS",
 *   "operationName": "DELETE"
 * }
 *
 * Design Benefits:
 * - Provides consistent response structure across different operation types
 * - Allows clients to programmatically determine operation success
 * - Enables clear communication of what operation was performed
 * - Supports internationalization by separating status from human-readable messages
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationStatusModel {
	private String operationResult;
	private String operationName;

}