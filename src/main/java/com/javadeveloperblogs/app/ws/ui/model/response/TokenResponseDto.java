package com.javadeveloperblogs.app.ws.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing authentication tokens
 * returned by the server after login or token refresh.
 * <p>
 * Contains access token, refresh token, token type, expiration
 * information, and issue timestamps. Sensitive values are masked
 * in the {@code toString()} method for security.
 * </p>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long expiresIn;
}
