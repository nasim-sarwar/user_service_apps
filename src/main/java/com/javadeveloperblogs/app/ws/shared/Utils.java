package com.javadeveloperblogs.app.ws.shared;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Date;
import java.util.Random;

import javax.crypto.SecretKey;

import com.javadeveloperblogs.app.ws.security.SecurityConstants;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * Utility service providing common helper functions for the application.
 *
 * This service class centralizes utility operations used throughout the application,
 * including secure random ID generation, JWT token management for authentication and
 * verification workflows, and token expiration validation. It leverages cryptographically
 * secure random number generation and JWT (JSON Web Token) standards for security-critical
 * operations.
 *
 * Primary Responsibilities:
 * - Generate unique, random public identifiers for users and addresses
 * - Create and validate JWT tokens for email verification and password reset workflows
 * - Check token expiration status for authentication and verification processes
 *
 * Security Features:
 * - Uses SecureRandom for cryptographically strong random number generation
 * - Generates alphanumeric IDs (0-9, A-Z, a-z) for public identifiers
 * - Creates signed JWT tokens using HMAC-SHA algorithm
 * - Validates token integrity and expiration using standard JWT claims
 *
 * ID Generation:
 * - User IDs: Unique public identifiers for users (separate from database primary keys)
 * - Address IDs: Unique public identifiers for addresses
 * - Generated IDs are URL-safe and suitable for external exposure in REST APIs
 *
 * Token Management:
 * - Email Verification Tokens: Time-limited tokens sent to users for email verification
 * - Password Reset Tokens: Time-limited tokens sent to users for password recovery
 * - All tokens are signed with application secret key from SecurityConstants
 * - Token expiration time is configurable via SecurityConstants.EXPIRATION_TIME
 *
 * JWT Token Structure:
 * - Subject: Contains the userId for which the token was generated
 * - IssuedAt: Timestamp when the token was created
 * - Expiration: Timestamp when the token expires
 * - Signature: HMAC-SHA signature for integrity verification
 *
 * Usage Examples:
 * - String userId = utils.generateUserId(30); // Generate 30-character user ID
 * - String token = utils.generateEmailVerificationToken(userId); // Create verification token
 * - boolean expired = Utils.hasTokenExpired(token); // Check if token is still valid
 *
 * Thread Safety:
 * - SecureRandom is thread-safe for concurrent ID generation
 * - JWT operations are stateless and safe for concurrent use
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Service
public class Utils {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String generateUserId(int length) {
        return generateRandomString(length);
    }

    public String generateAddressId(int length) {
        return generateRandomString(length);
    }

    private String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }

    public static boolean hasTokenExpired(String token) {
        boolean returnValue = false;

        try {

            byte[] secretKeyBytes = SecurityConstants.getTokenSecret().getBytes();
            SecretKey key = Keys.hmacShaKeyFor(secretKeyBytes);

            JwtParser parser = Jwts.parser().verifyWith(key).build();

            Claims claims = parser.parseSignedClaims(token).getPayload();

            Date tokenExpirationDate = claims.getExpiration();
            Date todayDate = new Date();

            returnValue = tokenExpirationDate.before(todayDate);
        } catch (ExpiredJwtException ex) {
            returnValue = true;
        }

        return returnValue;
    }

    public String generateEmailVerificationToken(String userId) {
        return generateToken(userId);
    }

    public String generatePasswordResetToken(String userId) {
        return generateToken(userId);
    }

    private String generateToken(String userId) {
        byte[] secretKeyBytes = SecurityConstants.getTokenSecret().getBytes();
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(userId)
                .expiration(Date.from(now.plusMillis(SecurityConstants.EXPIRATION_TIME)))
                .issuedAt(Date.from(now))
                .signWith(secretKey)
                .compact();
    }

}