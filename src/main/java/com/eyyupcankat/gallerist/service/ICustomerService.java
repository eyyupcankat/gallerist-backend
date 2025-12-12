package com.eyyupcankat.gallerist.service;

import com.eyyupcankat.gallerist.dto.DtoCustomer;
import com.eyyupcankat.gallerist.dto.DtoCustomerIU;

public interface ICustomerService {


    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
