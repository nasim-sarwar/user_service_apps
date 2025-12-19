package com.javadeveloperblogs.app.ws.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Application Properties Configuration Component
 *
 * This component provides centralized access to application-specific configuration
 * properties defined in external configuration files (application.properties,
 * application.yml, or environment variables). It acts as a wrapper around Spring's
 * Environment interface to provide type-safe, business-focused property access methods.
 *
 * <p>Primary Responsibilities:</p>
 * <ul>
 *   <li>Retrieving security-related configuration properties</li>
 *   <li>Providing a clean abstraction layer over raw property access</li>
 *   <li>Centralizing property key management to avoid string duplication</li>
 * </ul>
 *
 * <p>Configuration Properties:</p>
 * <ul>
 *   <li><strong>tokenSecret</strong> - Secret key used for JWT token signing and validation</li>
 * </ul>
 *
 * <p>Usage Example:</p>
 * <pre>
 * {@code @Autowired}
 * private AppProperties appProperties;
 *
 * String secret = appProperties.getTokenSecret();
 * </pre>
 *
 * <p><strong>Security Note:</strong> Sensitive properties like tokenSecret should be
 * externalized and never hardcoded or committed to version control. Consider using
 * environment variables, encrypted property files, or secret management systems
 * (e.g., Spring Cloud Config, HashiCorp Vault) in production environments.</p>
 *
 * <p><strong>Best Practice:</strong> For better type safety and validation, consider
 * migrating to {@code @ConfigurationProperties} annotation with a dedicated POJO class.</p>
 *
 * @author Nasim Sarwar
 * @since 2025
 * @see Environment
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 */
@Component
public class AppProperties {

	@Autowired
	private Environment env;

	public String getTokenSecret()
	{
		return env.getProperty("tokenSecret");
	}
}