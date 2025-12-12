package com.eyyupcankat.gallerist.controller.impl;

import com.eyyupcankat.gallerist.controller.BaseController;
import com.eyyupcankat.gallerist.controller.ControllerResponse;
import com.eyyupcankat.gallerist.controller.ICustomerController;
import com.eyyupcankat.gallerist.dto.DtoCustomer;
import com.eyyupcankat.gallerist.dto.DtoCustomerIU;
import com.eyyupcankat.gallerist.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerControllerImpl extends BaseController implements ICustomerController {

    @Autowired
    private ICustomerService customerService;


    @PostMapping("/save")
    @Override
    public ControllerResponse<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
        return ok(customerService.saveCustomer(dtoCustomerIU));
    }
}
