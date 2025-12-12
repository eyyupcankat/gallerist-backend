package com.eyyupcankat.gallerist.controller.impl;

import com.eyyupcankat.gallerist.controller.BaseController;
import com.eyyupcankat.gallerist.controller.ControllerResponse;
import com.eyyupcankat.gallerist.controller.IAddressContoller;
import com.eyyupcankat.gallerist.dto.DtoAddress;
import com.eyyupcankat.gallerist.dto.DtoAddressIU;
import com.eyyupcankat.gallerist.service.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressControllerImpl extends BaseController implements IAddressContoller {


    @Autowired
    private IAddressService addressService;


    @PostMapping("/save")
    @Override
    public ControllerResponse<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
        return ok(addressService.saveAddress(dtoAddressIU));
    }
}
