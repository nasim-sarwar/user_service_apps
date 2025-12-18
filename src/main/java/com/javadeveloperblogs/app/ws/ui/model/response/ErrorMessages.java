package com.javadeveloperblogs.app.ws.ui.model.response;

/**
 * Enumeration of standardized error messages for API responses.
 *
 * This enum provides a centralized catalog of error messages used throughout the application,
 * ensuring consistent error communication to API clients. Each enum constant represents a
 * specific error scenario with a predefined, user-friendly error message.
 *
 * Benefits of Centralized Error Messages:
 * - Consistency: Same error scenarios produce identical messages across the application
 * - Maintainability: Messages can be updated in one location
 * - Type Safety: Compile-time validation of error message usage
 * - Documentation: Clear overview of all possible error conditions
 * - Internationalization Ready: Easy to extend for multi-language support
 *
 * Error Categories:
 * - Validation Errors: MISSING_REQUIRED_FIELD
 * - Data Integrity: RECORD_ALREADY_EXISTS, NO_RECORD_FOUND
 * - System Errors: INTERNAL_SERVER_ERROR
 * - Authentication/Authorization: AUTHENTICATION_FAILED, EMAIL_ADDRESS_NOT_VERIFIED
 * - Operation Failures: COULD_NOT_UPDATE_RECORD, COULD_NOT_DELETE_RECORD
 *
 * Usage Example:
 * throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
 *
 * or in exception handlers:
 * return ErrorMessage.builder()
 *     .message(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage())
 *     .build();
 *
 * Note: These messages are designed for client consumption and should not expose
 * sensitive system details or security vulnerabilities.
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
public enum ErrorMessages {

    MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    INTERNAL_SERVER_ERROR("Internal server error"),
    NO_RECORD_FOUND("Record with provided id is not found"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified");


    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}