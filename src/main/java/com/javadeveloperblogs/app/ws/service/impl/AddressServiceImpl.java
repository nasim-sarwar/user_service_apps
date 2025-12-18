package com.javadeveloperblogs.app.ws.service.impl;

import com.javadeveloperblogs.app.ws.service.AddressService;
import com.javadeveloperblogs.app.ws.shared.dto.AddressDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {
    /**
     * @param userId
     * @return
     */
    @Override
    public List<AddressDTO> getAddresses(String userId) {
        return List.of();
    }

    /**
     * @param addressId
     * @return
     */
    @Override
    public AddressDTO getAddress(String addressId) {
        return null;
    }
}
