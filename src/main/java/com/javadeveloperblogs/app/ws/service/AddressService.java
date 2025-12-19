package com.javadeveloperblogs.app.ws.service;

import com.javadeveloperblogs.app.ws.shared.dto.AddressDTO;

import java.util.List;

/**
 * Address Service Interface
 *
 * This service interface defines the contract for address management operations within the application.
 * It provides methods for retrieving address information associated with user accounts, supporting
 * scenarios where users maintain multiple addresses (shipping, billing, home, work, etc.).
 *
 * <p><strong>Core Operations:</strong></p>
 * <ul>
 *   <li><strong>getAddresses</strong>: Retrieves all addresses associated with a specific user
 *       <br>Returns an empty list if the user has no saved addresses
 *       <br>Useful for displaying address selection during checkout or profile management</li>
 *   <li><strong>getAddress</strong>: Retrieves a single address by its unique address ID
 *       <br>Used for address details, editing, or order fulfillment</li>
 * </ul>
 *
 * <p><strong>Use Cases:</strong></p>
 * <ul>
 *   <li>E-commerce checkout: Display saved shipping/billing addresses</li>
 *   <li>User profile management: List and manage multiple addresses</li>
 *   <li>Order processing: Retrieve specific shipping or billing address details</li>
 *   <li>Address validation: Fetch existing address for verification or updates</li>
 * </ul>
 *
 * <p><strong>Implementation Responsibilities:</strong></p>
 * The implementing class should:
 * <ul>
 *   <li>Validate that the user ID exists before retrieving addresses</li>
 *   <li>Verify that the requested address belongs to the authenticated user (authorization check)</li>
 *   <li>Handle cases where address or user is not found gracefully</li>
 *   <li>Return addresses in a consistent order (e.g., default address first, then by creation date)</li>
 *   <li>Apply proper data access controls to prevent unauthorized address access</li>
 * </ul>
 *
 * <p><strong>Data Transfer Object Usage:</strong></p>
 * This service operates on {@link AddressDTO} objects containing:
 * <ul>
 *   <li>Address ID (public identifier)</li>
 *   <li>Street address, city, state/province, postal code, country</li>
 *   <li>Address type (shipping, billing, home, work, etc.)</li>
 *   <li>Default address flag</li>
 *   <li>Additional metadata (name, phone number for delivery)</li>
 * </ul>
 *
 * <p><strong>Security Considerations:</strong></p>
 * <ul>
 *   <li>Always verify that the requesting user owns the addresses being accessed</li>
 *   <li>The userId parameter should match the authenticated user's ID</li>
 *   <li>Implement authorization checks to prevent users from accessing others' addresses</li>
 *   <li>Consider PII protection requirements (GDPR, CCPA) when handling address data</li>
 * </ul>
 *
 * <p><strong>Expected Behavior:</strong></p>
 * <ul>
 *   <li>getAddresses() returns empty list (not null) when user has no addresses</li>
 *   <li>getAddress() throws AddressNotFoundException if address doesn't exist</li>
 *   <li>Both methods throw UnauthorizedException if user lacks access rights</li>
 * </ul>
 *
 * <p><strong>Future Extensions:</strong></p>
 * This interface may be extended to include:
 * <ul>
 *   <li>createAddress() - Add new address for a user</li>
 *   <li>updateAddress() - Modify existing address details</li>
 *   <li>deleteAddress() - Remove an address from user's account</li>
 *   <li>setDefaultAddress() - Mark an address as the default shipping/billing address</li>
 * </ul>
 *
 * <p><strong>Usage Example:</strong></p>
 * <pre>
 * {@code @Autowired}
 * private AddressService addressService;
 *
 * // Get all addresses for a user (e.g., during checkout)
 * List&lt;AddressDTO&gt; userAddresses = addressService.getAddresses(userId);
 *
 * // Get specific address details (e.g., for order fulfillment)
 * AddressDTO shippingAddress = addressService.getAddress(addressId);
 * System.out.println(shippingAddress.getCity() + ", " + shippingAddress.getState());
 * </pre>
 *
 * <p><strong>Integration Points:</strong></p>
 * <ul>
 *   <li>REST Controllers: Expose address endpoints for frontend consumption</li>
 *   <li>Order Service: Retrieve shipping/billing addresses during order placement</li>
 *   <li>User Service: Display address information in user profiles</li>
 *   <li>Validation Service: Verify address format and completeness</li>
 * </ul>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 * @see AddressDTO
 * @see UserService
 */
public interface AddressService {
    List<AddressDTO> getAddresses(String userId);
    AddressDTO getAddress(String addressId);
}