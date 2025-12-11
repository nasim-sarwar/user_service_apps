package com.javadeveloperblogs.app.ws.ui.controller;

import com.javadeveloperblogs.app.ws.service.UserService;
import com.javadeveloperblogs.app.ws.ui.model.request.*;
import com.javadeveloperblogs.app.ws.ui.model.response.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST controller responsible for handling all user-related API operations.
 * <p>
 * This controller exposes endpoints for user registration, authentication,
 * profile management, password operations, account status updates, searching,
 * statistics, and administrative functions. It serves as an entry point for
 * client applications to interact with the User Service layer.
 * </p>
 *
 * <p><b>Main Features:</b></p>
 * <ul>
 *     <li>Create, update, and delete users (CRUD operations)</li>
 *     <li>User login, logout, and token refresh</li>
 *     <li>Password reset, change, and verification workflows</li>
 *     <li>Email verification and re-verification</li>
 *     <li>Bulk user operations</li>
 *     <li>User search, filtering, and statistics</li>
 *     <li>Authenticated user profile handling</li>
 * </ul>
 *
 * <p>
 * All endpoints follow RESTful principles and use DTOs for request/response
 * communication. Validation is performed using {@code @Valid} annotations.
 * </p>
 *
 * @author Nasim
 * @version 1.0
 * @since 2025
 */
@RestController
@RequestMapping("/users")    // http://localhost:8080/users
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Create a new user (Registration)
     * POST /users
     */
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody CreateUserRequestDto userRequest) {
        UserResponseDto createdUser = userService.createUser(userRequest);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Get user by userId
     * GET /users/{userId}
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserByUserId(@PathVariable String userId) {
        UserResponseDto user = userService.getUserByUserId(userId);
        return ResponseEntity.ok(user);
    }

    /**
     * Get all users with pagination
     * GET /users?page=0&limit=10
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<UserResponseDto> users = userService.getUsers(page, limit);
        return ResponseEntity.ok(users);
    }

    /**
     * Update user details
     * PUT /users/{userId}
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable String userId,
            @Valid @RequestBody UpdateUserRequestDto userRequest) {
        UserResponseDto updatedUser = userService.updateUser(userId, userRequest);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Partial update of user
     * PATCH /users/{userId}
     */
    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDto> partialUpdateUser(
            @PathVariable String userId,
            @RequestBody UpdateUserRequestDto userRequest) {
        UserResponseDto updatedUser = userService.partialUpdateUser(userId, userRequest);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Delete user
     * DELETE /users/{userId}
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    /**
     * User login
     * POST /users/login
     */
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> loginUser(@Valid @RequestBody LoginRequestDto loginRequest) {
        UserResponseDto userResponse = userService.loginUser(loginRequest);
        return ResponseEntity.ok(userResponse);
    }

    /**
     * User logout
     * POST /users/logout
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(@RequestHeader("Authorization") String token) {
        userService.logoutUser(token);
        return ResponseEntity.ok().build();
    }

    /**
     * Refresh authentication token
     * POST /users/refresh-token
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResponseDto> refreshToken(@RequestBody RefreshTokenRequestDto refreshRequest) {
        TokenResponseDto tokenResponse = userService.refreshToken(refreshRequest);
        return ResponseEntity.ok(tokenResponse);
    }

    /**
     * Request password reset
     * POST /users/forgot-password
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<MessageResponseDto> forgotPassword(@Valid @RequestBody ForgotPasswordRequestDto request) {
        MessageResponseDto response = userService.forgotPassword(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Reset password with token
     * PUT /users/reset-password
     */
    @PutMapping("/reset-password")
    public ResponseEntity<MessageResponseDto> resetPassword(@Valid @RequestBody ResetPasswordRequestDto request) {
        MessageResponseDto response = userService.resetPassword(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Change password (authenticated user)
     * PUT /users/{userId}/password
     */
    @PutMapping("/{userId}/password")
    public ResponseEntity<MessageResponseDto> changePassword(
            @PathVariable String userId,
            @Valid @RequestBody ChangePasswordRequestDto request) {
        MessageResponseDto response = userService.changePassword(userId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Resend verification email
     * POST /users/resend-verification
     */
    @PostMapping("/resend-verification")
    public ResponseEntity<MessageResponseDto> resendVerificationEmail(@Valid @RequestBody ResendVerificationRequestDto request) {
        MessageResponseDto response = userService.resendVerificationEmail(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Verify email with token
     * GET /users/verify-email/{token}
     */
    @GetMapping("/verify-email/{token}")
    public ResponseEntity<MessageResponseDto> verifyEmail(@PathVariable String token) {
        MessageResponseDto response = userService.verifyEmail(token);
        return ResponseEntity.ok(response);
    }

    /**
     * Manual email verification (admin only)
     * PUT /users/{userId}/verify-email
     */
    @PutMapping("/{userId}/verify-email")
    public ResponseEntity<UserResponseDto> verifyEmailManually(@PathVariable String userId) {
        UserResponseDto user = userService.verifyEmailManually(userId);
        return ResponseEntity.ok(user);
    }

    /**
     * Get user profile
     * GET /users/{userId}/profile
     */
    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserResponseDto> getUserProfile(@PathVariable String userId) {
        UserResponseDto userProfile = userService.getUserProfile(userId);
        return ResponseEntity.ok(userProfile);
    }

    /**
     * Update user profile
     * PUT /users/{userId}/profile
     */
    @PutMapping("/{userId}/profile")
    public ResponseEntity<UserResponseDto> updateUserProfile(
            @PathVariable String userId,
            @Valid @RequestBody UpdateUserRequestDto profileRequest) {
        UserResponseDto updatedProfile = userService.updateUserProfile(userId, profileRequest);
        return ResponseEntity.ok(updatedProfile);
    }

    /**
     * Get current authenticated user
     * GET /users/me
     */
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getCurrentUser(@RequestHeader("Authorization") String token) {
        UserResponseDto user = userService.getCurrentUser(token);
        return ResponseEntity.ok(user);
    }

    /**
     * Search users by first name
     * GET /users/search?firstName={name}
     */
    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDto>> searchUsers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String q,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<UserResponseDto> users = userService.searchUsers(firstName, lastName, email, q, page, limit);
        return ResponseEntity.ok(users);
    }

    /**
     * Filter users by verification status
     * GET /users?verified={true/false}
     */
    @GetMapping(params = "verified")
    public ResponseEntity<List<UserResponseDto>> getUsersByVerificationStatus(
            @RequestParam Boolean verified,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<UserResponseDto> users = userService.getUsersByVerificationStatus(verified, page, limit);
        return ResponseEntity.ok(users);
    }

    /**
     * Get total user count
     * GET /users/count
     */
    @GetMapping("/count")
    public ResponseEntity<CountResponseDto> getUserCount() {
        CountResponseDto count = userService.getUserCount();
        return ResponseEntity.ok(count);
    }

    /**
     * Get user statistics
     * GET /users/stats
     */
    @GetMapping("/stats")
    public ResponseEntity<UserStatsResponseDto> getUserStats() {
        UserStatsResponseDto stats = userService.getUserStats();
        return ResponseEntity.ok(stats);
    }

    /**
     * Enable/disable user account
     * PUT /users/{userId}/status
     */
    @PutMapping("/{userId}/status")
    public ResponseEntity<UserResponseDto> updateUserStatus(
            @PathVariable String userId,
            @Valid @RequestBody UserStatusRequestDto statusRequest) {
        UserResponseDto user = userService.updateUserStatus(userId, statusRequest);
        return ResponseEntity.ok(user);
    }

    /**
     * Bulk user creation
     * POST /users/bulk
     */
    @PostMapping("/bulk")
    public ResponseEntity<BulkUserResponseDto> createUsersBulk(@Valid @RequestBody List<CreateUserRequestDto> users) {
        BulkUserResponseDto response = userService.createUsersBulk(users);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Bulk user deletion
     * DELETE /users/bulk
     */
    @DeleteMapping("/bulk")
    public ResponseEntity<MessageResponseDto> deleteUsersBulk(@RequestBody List<String> userIds) {
        MessageResponseDto response = userService.deleteUsersBulk(userIds);
        return ResponseEntity.ok(response);
    }

    /**
     * Check verification status
     * GET /users/{userId}/verification-status
     */
    @GetMapping("/{userId}/verification-status")
    public ResponseEntity<VerificationStatusResponseDto> getVerificationStatus(@PathVariable String userId) {
        VerificationStatusResponseDto status = userService.getVerificationStatus(userId);
        return ResponseEntity.ok(status);
    }

    /**
     * Update email (triggers re-verification)
     * PUT /users/{userId}/email
     */
    @PutMapping("/{userId}/email")
    public ResponseEntity<UserResponseDto> updateEmail(
            @PathVariable String userId,
            @Valid @RequestBody UpdateEmailRequestDto request) {
        UserResponseDto user = userService.updateEmail(userId, request);
        return ResponseEntity.ok(user);
    }

    /**
     * Self-deletion (soft delete)
     * DELETE /users/me
     */
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteCurrentUser(@RequestHeader("Authorization") String token) {
        userService.deleteCurrentUser(token);
        return ResponseEntity.noContent().build();
    }
}
