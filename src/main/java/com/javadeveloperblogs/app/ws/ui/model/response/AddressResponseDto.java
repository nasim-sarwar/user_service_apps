package com.javadeveloperblogs.app.ws.ui.model.response;
/**
 * Data Transfer Object (DTO) used for returning address information
 * in API responses. This class represents a user's address details
 * including city, country, postal code, and address type.
 *
 * <p>
 * It is typically used by controllers and service layers to send
 * structured address data back to the client without exposing
 * internal entity models.
 * </p>
 *
 * <p><b>Fields Included:</b></p>
 * <ul>
 *     <li>addressId — Unique identifier for the address</li>
 *     <li>city — City name</li>
 *     <li>country — Country name</li>
 *     <li>streetName — Street or locality name</li>
 *     <li>postalCode — ZIP or postal code</li>
 *     <li>type — Address type (e.g., home, office, billing, shipping)</li>
 * </ul>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {

    private String addressId;
    private String type;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
