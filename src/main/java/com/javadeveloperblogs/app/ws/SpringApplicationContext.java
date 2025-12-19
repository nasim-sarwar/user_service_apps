package com.javadeveloperblogs.app.ws;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring Application Context Holder Utility
 *
 * This utility class provides static access to the Spring ApplicationContext,
 * allowing beans to be retrieved from anywhere in the application without
 * requiring dependency injection. It implements ApplicationContextAware to
 * receive the ApplicationContext from the Spring container during initialization.
 *
 * <p>Primary Use Cases:</p>
 * <ul>
 *   <li>Accessing Spring beans from non-Spring managed classes</li>
 *   <li>Retrieving beans dynamically at runtime</li>
 *   <li>Providing ApplicationContext access to utility classes</li>
 * </ul>
 *
 * <p>Usage Example:</p>
 * <pre>
 * UserService userService = (UserService) SpringApplicationContext.getBean("userService");
 * </pre>
 *
 * <p><strong>Note:</strong> This class must be registered as a Spring bean (via @Component
 * or XML configuration) for the ApplicationContext to be properly injected.</p>
 *
 * <p><strong>Warning:</strong> While convenient, static access to the ApplicationContext
 * should be used sparingly as it can make code harder to test and may hide dependencies.
 * Prefer constructor or setter injection when possible.</p>
 *
 * @author Nasim Sarwar
 * @since 2025
 * @see ApplicationContextAware
 * @see ApplicationContext
 */
public class SpringApplicationContext implements ApplicationContextAware {
	private static ApplicationContext CONTEXT;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		CONTEXT = context;
	}

	public static Object getBean(String beanName) {
		return CONTEXT.getBean(beanName);
	}
}