package com.javadeveloperblogs.app.ws.io.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
/**
 * Entity class representing an address record in the system.
 *
 * <p>This class maps to the <strong>addresses</strong> table and stores
 * detailed address information including city, country, street name,
 * postal code, and type. It also maintains audit timestamps for
 * creation and updates.</p>
 *
 * <p>Validation constraints ensure that required fields are provided
 * and do not exceed defined length limits.</p>
 *
 * <p>Lifecycle callbacks (@PrePersist, @PreUpdate) automatically
 * populate auditing fields.</p>
 *
 * @author Nasim Sarwar
 * @since 2025
 */

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {

    @Id
    @Column(name = "address_id", length = 50)
    private String addressId;

    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City cannot exceed 100 characters")
    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @NotBlank(message = "Country is required")
    @Size(max = 100, message = "Country cannot exceed 100 characters")
    @Column(name = "country", nullable = false, length = 100)
    private String country;

    @NotBlank(message = "Street name is required")
    @Size(max = 200, message = "Street name cannot exceed 200 characters")
    @Column(name = "street_name", nullable = false, length = 200)
    private String streetName;

    @NotBlank(message = "Postal code is required")
    @Size(max = 20, message = "Postal code cannot exceed 20 characters")
    @Column(name = "postal_code", nullable = false, length = 20)
    private String postalCode;

    @NotBlank(message = "Address type is required")
    @Size(max = 50, message = "Type cannot exceed 50 characters")
    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
