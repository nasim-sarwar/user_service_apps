package com.javadeveloperblogs.app.ws.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * REST response model representing user information exposed to API clients.
 *
 * This class serves as a Data Transfer Object (DTO) for user data in API responses,
 * providing a simplified view of user information without sensitive details like
 * passwords or internal database IDs. It includes the user's basic profile information
 * and associated addresses.
 *
 * Key Characteristics:
 * - Uses public userId instead of internal database ID for security
 * - Excludes sensitive fields (encryptedPassword, emailVerificationToken)
 * - Includes nested address information through AddressesRest list
 * - Designed for JSON serialization in REST API responses
 *
 * Usage:
 * This model is typically returned by user-related endpoints such as:
 * - GET /users/{userId} - Retrieve single user details
 * - GET /users - List multiple users
 * - POST /users - Create user (response after creation)
 * - PUT /users/{userId} - Update user (response after update)
 *
 * The separation between entity models and REST models provides:
 * - Security through selective field exposure
 * - API stability independent of database schema changes
 * - Clean separation of concerns between layers
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
public class UserRest {
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private List<AddressesRest> addresses;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<AddressesRest> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressesRest> addresses) {
		this.addresses = addresses;
	}

}