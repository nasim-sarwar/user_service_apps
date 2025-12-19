package com.javadeveloperblogs.app.ws.io.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

/**
 * JPA entity representing an authority (permission) in the application's authorization system.
 *
 * This entity maps to the "authorities" table and defines granular permissions that can be
 * assigned to roles. Authorities represent specific actions or access rights that users can
 * perform within the application, forming the foundation of the permission-based security model.
 *
 * Relationships:
 * - Many-to-Many with RoleEntity: Multiple roles can share the same authority
 *
 * Authority Naming Convention:
 * Authorities typically follow patterns like:
 * - READ_AUTHORITY, WRITE_AUTHORITY, DELETE_AUTHORITY (operation-based)
 * - USER_READ, USER_WRITE, USER_DELETE (resource-based)
 *
 * Design Pattern:
 * This entity works with RoleEntity to implement a flexible RBAC (Role-Based Access Control)
 * system where:
 * - Users are assigned Roles
 * - Roles contain Authorities
 * - Authorities define what actions can be performed
 *
 * The authority name is limited to 20 characters and must be unique to prevent
 * permission conflicts.
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Entity
@Table(name="authorities")
public class AuthorityEntity implements Serializable {

	private static final long serialVersionUID = -5828101164006114538L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable=false, length=20)
	private String name;

	@ManyToMany(mappedBy="authorities")
	private Collection<RoleEntity> roles;

	public AuthorityEntity() {}

	public AuthorityEntity(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleEntity> roles) {
		this.roles = roles;
	}

}
