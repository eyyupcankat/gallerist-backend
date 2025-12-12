package com.eyyupcankat.gallerist.service;

import com.eyyupcankat.gallerist.dto.DtoAddress;
import com.eyyupcankat.gallerist.dto.DtoAddressIU;

public interface IAddressService {


    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);

}
