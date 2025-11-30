package com.javadeveloperblogs.app.ws.shared.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String addressId;
    private String type;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private UserDto userDetails;
}
