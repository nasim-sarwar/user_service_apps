package com.javadeveloperblogs.app.ws.service.impl;

import com.javadeveloperblogs.app.ws.io.Repository.AddressRepository;
import com.javadeveloperblogs.app.ws.io.Repository.UserRepository;
import com.javadeveloperblogs.app.ws.io.entity.AddressEntity;
import com.javadeveloperblogs.app.ws.io.entity.UserEntity;
import com.javadeveloperblogs.app.ws.service.AddressService;
import com.javadeveloperblogs.app.ws.shared.dto.AddressDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the AddressService interface that handles business logic
 * for address-related operations.
 *
 * <p>This service class provides functionality to retrieve user addresses from
 * the database and convert them to Data Transfer Objects (DTOs) for use in the
 * application layer. It acts as an intermediary between the controller layer
 * and the data access layer.</p>
 *
 * <p>Key responsibilities:</p>
 * <ul>
 *   <li>Retrieving all addresses associated with a specific user</li>
 *   <li>Retrieving a single address by its unique identifier</li>
 *   <li>Converting entity objects to DTOs using ModelMapper</li>
 * </ul>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * Retrieves all addresses associated with a specific user identified by their public user ID.
     *
     * <p>This method performs the following operations:</p>
     * <ol>
     *   <li>Looks up the user in the database using the provided userId</li>
     *   <li>If the user exists, fetches all associated addresses</li>
     *   <li>Converts each AddressEntity to AddressDTO using ModelMapper</li>
     *   <li>Returns a list of DTOs to the caller</li>
     * </ol>
     *
     * <p>If the user is not found, an empty list is returned instead of throwing
     * an exception, providing graceful handling of missing data.</p>
     *
     * @param userId the public user identifier (not the database primary key)
     * @return a List of AddressDTO objects containing all addresses for the user,
     *         or an empty list if the user is not found or has no addresses
     * @throws IllegalArgumentException if userId is null or empty
     */
    @Override
    public List<AddressDTO> getAddresses(String userId) {

        List<AddressDTO> returnValue = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        // Fetch user entity by userId
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null)
            return returnValue; // Return empty list if user not found

        // Fetch all addresses associated with the user
        Iterable<AddressEntity> listAddressEntities = addressRepository.findAllByUserDetails(userEntity);

        // Map each AddressEntity to AddressDTO and add to return list
        for(AddressEntity addressEntity : listAddressEntities) {
            returnValue.add(mapper.map(addressEntity, AddressDTO.class));
        }

        return returnValue;
    }

    /**
     * Retrieves a single address identified by its unique public address ID.
     *
     * <p>This method queries the database for an address using the provided
     * addressId and converts the result from an entity object to a DTO for
     * use in the application layer.</p>
     *
     * <p>Note: This method does not perform null checking on the retrieved
     * entity. If no address is found with the given addressId, ModelMapper
     * will attempt to map a null object, which may result in unexpected behavior
     * or exceptions.</p>
     *
     * @param addressId the public address identifier (not the database primary key)
     * @return an AddressDTO object containing the address details
     * @throws IllegalArgumentException if addressId is null or empty
     * @throws NullPointerException if no address is found with the given addressId
     *         (due to mapping a null entity)
     */
    @Override
    public AddressDTO getAddress(String addressId) {
        // Fetch address entity by addressId
        AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
        ModelMapper mapper = new ModelMapper();
        AddressDTO returnValue = mapper.map(addressEntity, AddressDTO.class);
        return returnValue;
    }
}