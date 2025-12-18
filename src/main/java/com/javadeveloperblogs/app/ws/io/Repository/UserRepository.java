package com.javadeveloperblogs.app.ws.io.Repository;

import com.javadeveloperblogs.app.ws.io.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Spring Data JPA repository interface for UserEntity data access operations.
 *
 * This repository provides CRUD operations and query methods for managing user records
 * in the database. It extends JpaRepository to inherit standard data access methods including
 * save, delete, findById, findAll, and more.
 *
 * Standard Inherited Operations:
 * - save(UserEntity): Create or update a user
 * - findById(Long): Retrieve a user by internal database ID
 * - findAll(): Retrieve all users
 * - delete(UserEntity): Remove a user from the database
 * - count(): Get total number of users
 *
 * Custom Query Methods:
 * Additional finder methods can be defined following Spring Data JPA naming conventions,
 * such as:
 * - findByEmail(String email)
 * - findByUserId(String userId)
 * - findByEmailVerificationToken(String token)
 *
 * Usage:
 * This repository is automatically implemented by Spring Data JPA at runtime and can be
 * injected into service classes for user data management operations.
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}