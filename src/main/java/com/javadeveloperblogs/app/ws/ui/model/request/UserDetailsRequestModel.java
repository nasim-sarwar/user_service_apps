package com.javadeveloperblogs.app.ws.ui.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * REST request model for creating or updating user accounts with complete details.
 *
 * This class serves as a Data Transfer Object (DTO) for capturing comprehensive user
 * information submitted by clients during user registration or profile update operations.
 * It includes both basic user profile data and associated addresses, enabling clients
 * to create or modify user accounts with multiple addresses in a single API call.
 *
 * Fields:
 * - firstName: User's first name
 * - lastName: User's last name
 * - email: User's email address (used for authentication and communication)
 * - password: User's plain-text password (will be encrypted before storage)
 * - addresses: List of addresses associated with the user (shipping, billing, etc.)
 *
 * Common Use Cases:
 * - User Registration: POST /users - Create new user account with initial profile and addresses
 * - Profile Update: PUT /users/{userId} - Update existing user information and addresses
 * - Admin Operations: Create or modify user accounts through administrative interfaces
 *
 * Validation Requirements:
 * - firstName and lastName should not be null or empty
 * - email should be validated for proper format and uniqueness
 * - password should meet complexity requirements (length, special characters, etc.)
 * - addresses list can be empty but individual addresses should be valid if provided
 *
 * Security Considerations:
 * - Password is transmitted in plain text, so HTTPS is mandatory
 * - Password should be immediately encrypted upon receipt
 * - Email should be verified before account activation
 * - Consider implementing CAPTCHA for registration to prevent automated abuse
 *
 * Example JSON Request (User Registration):
 * {
 *   "firstName": "John",
 *   "lastName": "Doe",
 *   "email": "john.doe@example.com",
 *   "password": "SecurePass123!",
 *   "addresses": [
 *     {
 *       "city": "Vancouver",
 *       "country": "Canada",
 *       "streetName": "123 Main Street",
 *       "postalCode": "V6B2M9",
 *       "type": "shipping"
 *     }
 *   ]
 * }
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsRequestModel {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<AddressRequestModel> addresses;

}