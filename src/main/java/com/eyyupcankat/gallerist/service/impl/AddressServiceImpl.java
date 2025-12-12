package com.eyyupcankat.gallerist.service.impl;

import com.eyyupcankat.gallerist.dto.DtoAddress;
import com.eyyupcankat.gallerist.dto.DtoAddressIU;
import com.eyyupcankat.gallerist.entity.Address;
import com.eyyupcankat.gallerist.exception.BaseException;
import com.eyyupcankat.gallerist.exception.ErrorMessage;
import com.eyyupcankat.gallerist.exception.MessageType;
import com.eyyupcankat.gallerist.repository.AddressRepository;
import com.eyyupcankat.gallerist.service.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    private Address createAddress(DtoAddressIU dtoAddressIU) {
        Address address = new Address();

        BeanUtils.copyProperties(dtoAddressIU, address);
        address.setCreateTime(new Date());

        return address;
    }

    @Override
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
        Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));
        DtoAddress dtoAddress = new DtoAddress();
        BeanUtils.copyProperties(savedAddress, dtoAddress);
        return dtoAddress;
    }




}
