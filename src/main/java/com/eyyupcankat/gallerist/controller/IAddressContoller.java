package com.eyyupcankat.gallerist.controller;

import com.eyyupcankat.gallerist.dto.DtoAddress;
import com.eyyupcankat.gallerist.dto.DtoAddressIU;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAddressContoller {

    public ControllerResponse<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU);

}
