package com.javadeveloperblogs.app.ws.io.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * JPA entity representing a physical address associated with a user.
 *
 * This entity maps to the "addresses" table and stores multiple address records for users,
 * supporting scenarios where users have different addresses for shipping, billing, or other purposes.
 * Each address is identified by a public addressId separate from the internal database ID.
 *
 * Relationships:
 * - Many-to-One with UserEntity: Multiple addresses can belong to a single user
 *
 * Address Types:
 * The type field categorizes addresses (e.g., "shipping", "billing", "home", "work")
 * and is limited to 10 characters for standardized classification.
 *
 * Field Constraints:
 * - city: Maximum 15 characters
 * - country: Maximum 15 characters
 * - streetName: Maximum 100 characters
 * - postalCode: Maximum 7 characters (supports various postal code formats)
 * - type: Maximum 10 characters
 *
 * Note: All address fields are mandatory (nullable=false) to ensure data completeness
 * for shipping, billing, or communication purposes.
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Entity(name="addresses")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class AddressEntity implements Serializable {

	private static final long serialVersionUID = 7809200551672852690L;

	@Id
	@GeneratedValue
	private long id;

	@Column(length=30, nullable=false)
	private String addressId;

	@Column(length=15, nullable=false)
	private String city;

	@Column(length=15, nullable=false)
	private String country;

	@Column(length=100, nullable=false)
	private String streetName;

	@Column(length=7, nullable=false)
	private String postalCode;

	@Column(length=10, nullable=false)
	private String type;

	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity userDetails;

}