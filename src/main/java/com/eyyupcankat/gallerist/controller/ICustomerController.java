package com.eyyupcankat.gallerist.controller;

import com.eyyupcankat.gallerist.dto.DtoCustomer;
import com.eyyupcankat.gallerist.dto.DtoCustomerIU;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICustomerController {

    ControllerResponse<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU);
}
