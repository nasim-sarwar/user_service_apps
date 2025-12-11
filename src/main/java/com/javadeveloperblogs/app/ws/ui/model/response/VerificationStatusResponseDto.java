package com.javadeveloperblogs.app.ws.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * Data Transfer Object (DTO) representing the verification status of a user's email.
 * <p>
 * This DTO provides details about the user's email verification, including whether the email
 * is verified, when the verification occurred, when the token was sent, and if the token has expired.
 * </p>
 *
 * <p>Typical usage: Returning verification status in response to API requests related to email verification.</p>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationStatusResponseDto {
    private String userId;
    private String email;
    private Boolean emailVerified;
    private LocalDateTime verificationDate;
    private Boolean canResendVerification;
}