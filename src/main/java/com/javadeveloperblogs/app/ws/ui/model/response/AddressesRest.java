package com.javadeveloperblogs.app.ws.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * REST response model representing address information with HATEOAS support.
 *
 * This class serves as a Data Transfer Object (DTO) for address data in API responses,
 * providing a clean representation of address information to API clients. By extending
 * RepresentationModel, it supports HATEOAS (Hypermedia as the Engine of Application State),
 * enabling the inclusion of hypermedia links that guide clients through available actions
 * and related resources.
 *
 * Fields:
 * - addressId: Public identifier for the address (not the internal database ID)
 * - city: City name where the address is located
 * - country: Country of the address
 * - streetName: Street address including number and street name
 * - postalCode: Postal or ZIP code for the address
 * - type: Address classification (e.g., "shipping", "billing", "home", "work")
 *
 * HATEOAS Capabilities:
 * Through RepresentationModel inheritance, this class can include hypermedia links such as:
 * - self: Link to retrieve this specific address
 * - user: Link to the user who owns this address
 * - update: Link to update the address
 * - delete: Link to delete the address
 *
 * Example JSON Response with HATEOAS:
 * {
 *   "addressId": "5pf7ajk83d",
 *   "city": "Vancouver",
 *   "country": "Canada",
 *   "streetName": "123 Main Street",
 *   "postalCode": "V6B2M9",
 *   "type": "shipping",
 *   "_links": {
 *     "self": {"href": "http://localhost:8080/users/1a2b3c/addresses/5pf7ajk83d"},
 *     "user": {"href": "http://localhost:8080/users/1a2b3c"}
 *   }
 * }
 *
 * Usage Context:
 * - Nested within UserRest responses to show user's addresses
 * - Standalone responses for address-specific endpoints
 * - List responses when retrieving all addresses for a user
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressesRest  {
	private String addressId;
	private String city;
	private String country;
	private String streetName;
	private String postalCode;
	private String type;

}