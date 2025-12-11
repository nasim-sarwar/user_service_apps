package com.javadeveloperblogs.app.ws.ui.model.response;

import java.util.List;
/**
 * Data Transfer Object (DTO) representing user information returned
 * from the server after creation, update, or retrieval operations.
 * <p>
 * This DTO includes user identification, personal details, email
 * verification status, and a list of associated addresses.
 * </p>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean emailVerificationStatus;
    private List<AddressResponseDto> addresses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Optional fields for login response
    private String accessToken;
    private String refreshToken;
    private Long tokenExpiresIn;
}