package com.eyyupcankat.gallerist.controller;

import com.eyyupcankat.gallerist.dto.DtoAccount;
import com.eyyupcankat.gallerist.dto.DtoAccountIU;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAccountController {

    public ControllerResponse<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU);
}
