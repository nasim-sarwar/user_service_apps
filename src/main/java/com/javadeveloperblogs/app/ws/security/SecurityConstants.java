package com.javadeveloperblogs.app.ws.security;

import com.javadeveloperblogs.app.ws.SpringApplicationContext;

/**
 * Centralized Security Configuration Constants
 *
 * This class defines core security parameters, time limits, HTTP headers, and URL patterns
 * used throughout the application's authentication and authorization system. By centralizing
 * these values, the application maintains consistency across security configurations and
 * simplifies maintenance when security parameters need adjustment.
 *
 * <p><strong>Token Expiration Settings:</strong></p>
 * <ul>
 *   <li><strong>EXPIRATION_TIME</strong>: JWT token lifetime for authentication (10 days = 864,000,000 ms)
 *       <br>Used for user session tokens after successful login</li>
 *   <li><strong>PASSWORD_RESET_EXPIRATION_TIME</strong>: Password reset token lifetime (1 hour = 3,600,000 ms)
 *       <br>Shorter expiration for security-sensitive password recovery operations</li>
 * </ul>
 *
 * <p><strong>HTTP Security Headers:</strong></p>
 * <ul>
 *   <li><strong>TOKEN_PREFIX</strong>: Standard Bearer token prefix for Authorization header ("Bearer ")</li>
 *   <li><strong>HEADER_STRING</strong>: HTTP header name for authentication tokens ("Authorization")</li>
 * </ul>
 *
 * <p><strong>Public Endpoint URLs (No Authentication Required):</strong></p>
 * <ul>
 *   <li><strong>SIGN_UP_URL</strong>: User registration endpoint ("/users")</li>
 *   <li><strong>VERIFICATION_EMAIL_URL</strong>: Email verification endpoint ("/users/email-verification")</li>
 *   <li><strong>PASSWORD_RESET_REQUEST_URL</strong>: Password reset request endpoint ("/users/password-reset-request")</li>
 *   <li><strong>PASSWORD_RESET_URL</strong>: Password reset completion endpoint ("/users/password-reset")</li>
 *   <li><strong>H2_CONSOLE</strong>: H2 database console access pattern ("/h2-console/**")
 *       <br><strong>Warning:</strong> Should be disabled or restricted in production environments</li>
 * </ul>
 *
 * <p><strong>Token Secret Management:</strong></p>
 * The getTokenSecret() method retrieves the JWT signing secret from application properties
 * via the AppProperties bean. This secret is used for:
 * <ul>
 *   <li>Signing JWT tokens during generation</li>
 *   <li>Verifying JWT token integrity during validation</li>
 *   <li>HMAC-SHA encryption for token security</li>
 * </ul>
 *
 * <p><strong>Security Best Practices:</strong></p>
 * <ul>
 *   <li>Token secret should be stored externally (environment variables, secrets manager)</li>
 *   <li>Token secret should be sufficiently long and complex (minimum 256 bits recommended)</li>
 *   <li>Token secret should never be hardcoded or committed to version control</li>
 *   <li>Different secrets should be used for different environments (dev, staging, production)</li>
 *   <li>H2 console should be disabled in production deployments</li>
 *   <li>Consider shorter token expiration times for high-security applications</li>
 *   <li>Implement token refresh mechanisms for better security without sacrificing UX</li>
 * </ul>
 *
 * <p><strong>Usage in Security Configuration:</strong></p>
 * These constants are referenced by:
 * <ul>
 *   <li>JWT authentication filters for token validation</li>
 *   <li>Security filter chains for endpoint authorization rules</li>
 *   <li>Token generation utilities for creating signed tokens</li>
 *   <li>Password reset and email verification services</li>
 * </ul>
 *
 * <p><strong>Example Usage:</strong></p>
 * <pre>
 * // In Security Configuration
 * http.authorizeRequests()
 *     .antMatchers(SecurityConstants.SIGN_UP_URL).permitAll()
 *     .antMatchers(SecurityConstants.H2_CONSOLE).permitAll();
 *
 * // In JWT Filter
 * String token = request.getHeader(SecurityConstants.HEADER_STRING);
 * if (token != null && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
 *     String jwt = token.replace(SecurityConstants.TOKEN_PREFIX, "");
 *     // Validate using SecurityConstants.getTokenSecret()
 * }
 *
 * // In Token Generation
 * String token = Jwts.builder()
 *     .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
 *     .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
 *     .compact();
 * </pre>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 * @see AppProperties
 * @see com.javadeveloperblogs.app.ws.SpringApplicationContext
 */
public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final long PASSWORD_RESET_EXPIRATION_TIME = 3600000; // 1 hour
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";
    public static final String VERIFICATION_EMAIL_URL = "/users/email-verification";
    public static final String PASSWORD_RESET_REQUEST_URL = "/users/password-reset-request";
    public static final String PASSWORD_RESET_URL = "/users/password-reset";
    public static final String H2_CONSOLE = "/h2-console/**";

   public static String getTokenSecret()
    {
        // Bean name should match the class name with first letter lowercase (Spring's default naming convention)
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
        return appProperties.getTokenSecret();
    }
}