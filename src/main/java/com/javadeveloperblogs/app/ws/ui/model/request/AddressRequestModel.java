package com.javadeveloperblogs.app.ws.ui.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * REST request model for creating or updating address information.
 *
 * This class serves as a Data Transfer Object (DTO) for capturing address details
 * submitted by clients when adding or modifying addresses associated with user accounts.
 * It is typically used as a nested object within UserDetailsRequestModel or as a
 * standalone request for address-specific endpoints.
 *
 * Fields:
 * - city: The city name where the address is located
 * - country: The country of the address
 * - streetName: Complete street address including number and street name
 * - postalCode: Postal code or ZIP code for the address location
 * - type: Address classification/category (e.g., "shipping", "billing", "home", "work")
 *
 * Address Types:
 * Common type values include:
 * - "shipping": Primary delivery address for orders
 * - "billing": Address for billing and invoicing purposes
 * - "home": Personal residence address
 * - "work": Business or office address
 * Applications may define custom types based on business requirements
 *
 * Common Use Cases:
 * - User Registration: Creating initial addresses along with user account
 * - Profile Management: Adding new addresses to existing user accounts
 * - Checkout Process: Adding delivery addresses during order placement
 * - Address Updates: Modifying existing address details
 *
 * Validation Requirements:
 * - All fields should be required (not null or empty) for data completeness
 * - city: Should not exceed 15 characters (database constraint)
 * - country: Should not exceed 15 characters (database constraint)
 * - streetName: Should not exceed 100 characters (database constraint)
 * - postalCode: Should not exceed 7 characters (database constraint)
 * - type: Should not exceed 10 characters (database constraint)
 * - Consider validating postalCode format based on country
 * - Consider validating city and country against recognized lists
 *
 * Example JSON Request (Nested in User Registration):
 * {
 *   "city": "Vancouver",
 *   "country": "Canada",
 *   "streetName": "123 Main Street",
 *   "postalCode": "V6B2M9",
 *   "type": "shipping"
 * }
 *
 * Example JSON Request (Standalone Address Creation):
 * POST /users/{userId}/addresses
 * {
 *   "city": "Toronto",
 *   "country": "Canada",
 *   "streetName": "456 Queen Street West",
 *   "postalCode": "M5V2A8",
 *   "type": "billing"
 * }
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestModel {
	private String city;
	private String country;
	private String streetName;
	private String postalCode;
	private String type;

}