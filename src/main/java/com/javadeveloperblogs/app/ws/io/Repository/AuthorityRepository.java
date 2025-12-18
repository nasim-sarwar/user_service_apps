package com.javadeveloperblogs.app.ws.io.Repository;

import com.javadeveloperblogs.app.ws.io.entity.AuthorityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository interface for AuthorityEntity data access operations.
 *
 * This repository provides CRUD operations and custom query methods for managing authority
 * (permission) records in the application's authorization system. It extends CrudRepository
 * to inherit standard data access methods for authority persistence and retrieval.
 *
 * Standard Inherited Operations:
 * - save(AuthorityEntity): Create or update an authority
 * - findById(Long): Retrieve an authority by internal database ID
 * - findAll(): Retrieve all authorities
 * - delete(AuthorityEntity): Remove an authority from the database
 * - count(): Get total number of authorities
 *
 * Custom Query Methods:
 * - findByName(String): Retrieves an authority by its unique name (e.g., "READ_AUTHORITY",
 *   "WRITE_AUTHORITY"). This method is essential for assigning specific permissions to roles
 *   during role creation or modification, and for checking if an authority already exists
 *   before creating a new one.
 *
 * Usage:
 * This repository is automatically implemented by Spring Data JPA at runtime and is typically
 * used in service classes for:
 * - Initializing default authorities during application startup
 * - Assigning authorities to roles
 * - Validating permission existence before assignment
 * - Managing the permission hierarchy in the RBAC system
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Repository
public interface AuthorityRepository extends CrudRepository<AuthorityEntity, Long> {
	AuthorityEntity findByName(String name);
}