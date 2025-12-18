package com.javadeveloperblogs.app.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the User Service.
 * This class serves as the entry point for the Spring Boot application,
 * configuring and initializing the application context.
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}