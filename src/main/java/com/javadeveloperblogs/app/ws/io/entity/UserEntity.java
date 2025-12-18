package com.javadeveloperblogs.app.ws.io.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * JPA entity representing a user in the application.
 *
 * This entity maps to the "users" table in the database and contains core user information
 * including authentication credentials, profile details, and email verification status.
 * It maintains relationships with addresses and roles for complete user management.
 *
 * Relationships:
 * - One-to-Many with AddressEntity: A user can have multiple addresses
 * - Many-to-Many with RoleEntity: A user can have multiple roles for authorization
 *
 * Features:
 * - Email verification workflow with token and status tracking
 * - Encrypted password storage for security
 * - Public user ID (userId) separate from internal database ID for external exposure
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
 
	private static final long serialVersionUID = 5313493413859894403L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String userId;

	@Column(nullable=false, length=50)
	private String firstName;
	
	@Column(nullable=false, length=50)
	private String lastName;
	
	@Column(nullable=false, length=120)
	private String email;
	
	@Column(nullable=false)
	private String encryptedPassword;
	
	private String emailVerificationToken;
	
	@Column(nullable=false)
	private Boolean emailVerificationStatus = false;
	
	@OneToMany(mappedBy="userDetails", cascade=CascadeType.ALL)
	private List<AddressEntity> addresses;
	
	@ManyToMany(cascade= { CascadeType.PERSIST }, fetch = FetchType.EAGER )
	@JoinTable(name="users_roles", 
			joinColumns=@JoinColumn(name="users_id",referencedColumnName="id"), 
			inverseJoinColumns=@JoinColumn(name="roles_id",referencedColumnName="id"))
	private Collection<RoleEntity> roles;



}
