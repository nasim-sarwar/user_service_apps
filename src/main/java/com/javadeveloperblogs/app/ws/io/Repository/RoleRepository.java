package com.javadeveloperblogs.app.ws.io.Repository;

import com.javadeveloperblogs.app.ws.io.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Role entities in the database.
 * <p>
 * This interface extends Spring Data's CrudRepository to provide standard
 * CRUD operations for {@link RoleEntity} objects. It includes custom query
 * methods for role-specific data access operations.
 * </p>
 * <p>
 * Spring Data JPA automatically generates the implementation of this interface
 * at runtime, eliminating the need for boilerplate data access code.
 * </p>
 *
 * @author Nasim Sarwar
 * @since 2025
 * @version 1.0
 * @see RoleEntity
 * @see CrudRepository
 */
@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

	/**
	 * Finds a role by its name.
	 * <p>
	 * This is a derived query method where Spring Data JPA automatically
	 * generates the implementation based on the method name convention.
	 * </p>
	 *
	 * @param name the name of the role to find
	 * @return the RoleEntity with the specified name, or null if not found
	 */
	RoleEntity findByName(String name);
}