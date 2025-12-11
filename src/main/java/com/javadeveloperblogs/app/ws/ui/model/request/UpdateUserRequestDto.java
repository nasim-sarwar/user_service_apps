package com.javadeveloperblogs.app.ws.ui.model.request;

import jakarta.validation.constraints.Size;
/**
 * Data Transfer Object (DTO) used for updating user information.
 * <p>
 * This DTO allows partial or full updates of user fields such as first name,
 * last name, and email. Validation annotations ensure that the input
 * conforms to required size and format constraints.
 * </p>
 *
 * <p><b>Validation Rules:</b></p>
 * <ul>
 *     <li>{@code firstName} — Optional, max 50 characters</li>
 *     <li>{@code lastName} — Optional, max 50 characters</li>
 *     <li>{@code email} — Optional, must be a valid email address, max 120 characters</li>
 * </ul>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {

    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;

    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;

    // Note: Email update usually requires separate endpoint with verification
    // Addresses can be updated separately
    private List<AddressRequestDto> addresses;
}
