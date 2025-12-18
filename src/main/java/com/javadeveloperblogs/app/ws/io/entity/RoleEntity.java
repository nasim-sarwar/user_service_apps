package com.javadeveloperblogs.app.ws.io.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
/**
 * JPA entity representing a role in the application's role-based access control (RBAC) system.
 *
 * This entity maps to the "roles" table and serves as an intermediary between users and
 * their specific permissions (authorities). Roles group related authorities together to
 * simplify permission management and user authorization.
 *
 * Relationships:
 * - Many-to-Many with UserEntity: Multiple users can share the same role
 * - Many-to-Many with AuthorityEntity: A role can contain multiple authorities/permissions
 *
 * Common role examples include: ROLE_USER, ROLE_ADMIN, ROLE_MODERATOR
 *
 * The role name is limited to 20 characters and should follow consistent naming conventions
 * for proper authorization handling throughout the application.
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Entity
@Table(name="roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 5605260522147928803L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(nullable=false, length=20)
	private String name;
	
	@ManyToMany(mappedBy="roles")
	private Collection<UserEntity> users;
	
	@ManyToMany(cascade= { CascadeType.PERSIST }, fetch = FetchType.EAGER )
	@JoinTable(name="roles_authorities", 
			joinColumns=@JoinColumn(name="roles_id",referencedColumnName="id"), 
			inverseJoinColumns=@JoinColumn(name="authorities_id",referencedColumnName="id"))
	private Collection<AuthorityEntity> authorities;
 	


}
