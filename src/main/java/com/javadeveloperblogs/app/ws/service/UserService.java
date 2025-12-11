package com.javadeveloperblogs.app.ws.service;

import com.javadeveloperblogs.app.ws.ui.model.request.*;
import com.javadeveloperblogs.app.ws.ui.model.response.*;
import java.util.List;

/**
 * Service interface for managing user operations in the application.
 *
 * <p>This interface defines the contract for all user-related business logic including
 * user lifecycle management (CRUD operations), authentication, authorization, email
 * verification, password management, and user analytics.</p>
 *
 * <h2>Primary Responsibilities:</h2>
 * <ul>
 *   <li><b>User Management:</b> Create, read, update, and delete user accounts</li>
 *   <li><b>Authentication:</b> Handle user login, logout, and token refresh operations</li>
 *   <li><b>Email Verification:</b> Manage email verification workflow and status</li>
 *   <li><b>Password Operations:</b> Support password changes, resets, and forgot password flows</li>
 *   <li><b>User Search & Filtering:</b> Query users by various criteria and verification status</li>
 *   <li><b>Bulk Operations:</b> Handle batch creation and deletion of users</li>
 *   <li><b>Analytics:</b> Provide user statistics and counts</li>
 * </ul>
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>RESTful operation support with full and partial updates</li>
 *   <li>Pagination support for list operations</li>
 *   <li>Token-based authentication and session management</li>
 *   <li>Email verification with manual override capability</li>
 *   <li>Flexible search with multiple filter criteria</li>
 *   <li>User status management and tracking</li>
 * </ul>
 *
 * <h2>Security Considerations:</h2>
 * <p>Implementations of this interface should enforce proper authorization checks,
 * validate user permissions, sanitize inputs, and handle sensitive operations
 * (password changes, email updates) with appropriate security measures.</p>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
public interface UserService {
    UserResponseDto getUserByUserId(String userId);

    UserResponseDto createUser(CreateUserRequestDto userRequest);

    List<UserResponseDto> getUsers(int page, int limit);

    UserResponseDto updateUser(String userId, UpdateUserRequestDto userRequest);

    UserResponseDto partialUpdateUser(String userId, UpdateUserRequestDto userRequest);

    void deleteUser(String userId);

    UserResponseDto loginUser(LoginRequestDto loginRequest);

    void logoutUser(String token);

    TokenResponseDto refreshToken(RefreshTokenRequestDto refreshRequest);

    MessageResponseDto forgotPassword(ForgotPasswordRequestDto request);

    MessageResponseDto resetPassword(ResetPasswordRequestDto request);

    MessageResponseDto changePassword(String userId, ChangePasswordRequestDto request);

    MessageResponseDto resendVerificationEmail(ResendVerificationRequestDto request);

    MessageResponseDto verifyEmail(String token);

    UserResponseDto verifyEmailManually(String userId);

    UserResponseDto getUserProfile(String userId);

    UserResponseDto updateUserProfile(String userId, UpdateUserRequestDto profileRequest);

    UserResponseDto getCurrentUser(String token);

    List<UserResponseDto> searchUsers(String firstName, String lastName, String email, String q, int page, int limit);

    List<UserResponseDto> getUsersByVerificationStatus(Boolean verified, int page, int limit);

    CountResponseDto getUserCount();

    UserStatsResponseDto getUserStats();

    UserResponseDto updateUserStatus(String userId, UserStatusRequestDto statusRequest);

    BulkUserResponseDto createUsersBulk(List<CreateUserRequestDto> users);

    MessageResponseDto deleteUsersBulk(List<String> userIds);

    VerificationStatusResponseDto getVerificationStatus(String userId);

    UserResponseDto updateEmail(String userId, UpdateEmailRequestDto request);

    void deleteCurrentUser(String token);
}