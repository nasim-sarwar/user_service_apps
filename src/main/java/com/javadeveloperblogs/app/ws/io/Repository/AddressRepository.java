package com.javadeveloperblogs.app.ws.io.Repository;

import java.util.List;

import com.javadeveloperblogs.app.ws.io.entity.AddressEntity;
import com.javadeveloperblogs.app.ws.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository interface for AddressEntity data access operations.
 *
 * This repository provides CRUD operations and custom query methods for managing address
 * records associated with users. It extends CrudRepository to inherit standard data access
 * methods for address persistence and retrieval.
 *
 * Standard Inherited Operations:
 * - save(AddressEntity): Create or update an address
 * - findById(Long): Retrieve an address by internal database ID
 * - findAll(): Retrieve all addresses
 * - delete(AddressEntity): Remove an address from the database
 * - count(): Get total number of addresses
 *
 * Custom Query Methods:
 * - findAllByUserDetails(UserEntity): Retrieves all addresses belonging to a specific user,
 *   useful for displaying a user's complete address list (shipping, billing, etc.)
 * - findByAddressId(String): Retrieves a single address by its public addressId,
 *   used for external API operations where the internal database ID should not be exposed
 *
 * Usage:
 * This repository is automatically implemented by Spring Data JPA at runtime and is typically
 * injected into service classes for address management operations such as retrieving user
 * addresses for order processing or profile management.
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
	List<AddressEntity> findAllByUserDetails(UserEntity userEntity);
	AddressEntity findByAddressId(String addressId);
}