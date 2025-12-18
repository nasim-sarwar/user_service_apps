package com.javadeveloperblogs.app.ws.io.Repository;

import java.util.List;

import com.javadeveloperblogs.app.ws.io.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;


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

    UserEntity findByEmail(String email);
    UserEntity findByUserId(String userId);
    UserEntity findUserByEmailVerificationToken(String token);

    @Query(
            value = "SELECT * FROM users u WHERE u.email_verification_status = true",
            countQuery = "SELECT COUNT(*) FROM users u WHERE u.email_verification_status = true",
            nativeQuery = true
    )
    Page<UserEntity> findAllUsersWithConfirmedEmailAddress(Pageable pageableRequest);

    @Query(value = "SELECT * FROM users u WHERE u.first_name = ?1", nativeQuery = true)
    List<UserEntity> findUserByFirstName(String firstName);

    @Query(value = "SELECT * FROM users u WHERE u.last_name = :lastName", nativeQuery = true)
    List<UserEntity> findUserByLastName(@Param("lastName") String lastName);

    // ❗ FIXED → added alias u.first_name and u.last_name
    // @Query(value = "SELECT * FROM users u WHERE u.first_name LIKE %:keyword% OR u.last_name LIKE %:keyword%", nativeQuery = true)
    List<UserEntity> findUsersByKeyword(@Param("keyword") String keyword);

    //  @Query(value = "SELECT u.first_name, u.last_name FROM users u WHERE u.first_name LIKE %:keyword% OR u.last_name LIKE %:keyword%", nativeQuery = true)
    List<Object[]> findUserFirstNameAndLastNameByKeyword(@Param("keyword") String keyword);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users u SET u.email_verification_status = :emailVerificationStatus WHERE u.user_id = :userId", nativeQuery = true)
    void updateUserEmailVerificationStatus(
            @Param("emailVerificationStatus") boolean emailVerificationStatus,
            @Param("userId") String userId);

    @Query("SELECT user FROM UserEntity user WHERE user.userId = :userId")
    UserEntity findUserEntityByUserId(@Param("userId") String userId);

    @Query("SELECT user.firstName, user.lastName FROM UserEntity user WHERE user.userId = :userId")
    List<Object[]> getUserEntityFullNameById(@Param("userId") String userId);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.emailVerificationStatus = :emailVerificationStatus WHERE u.userId = :userId")
    void updateUserEntityEmailVerificationStatus(
            @Param("emailVerificationStatus") boolean emailVerificationStatus,
            @Param("userId") String userId);

}