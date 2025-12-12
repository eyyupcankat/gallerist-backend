package com.eyyupcankat.gallerist.controller.impl;

import com.eyyupcankat.gallerist.controller.BaseController;
import com.eyyupcankat.gallerist.controller.ControllerResponse;
import com.eyyupcankat.gallerist.controller.IAccountController;
import com.eyyupcankat.gallerist.dto.DtoAccount;
import com.eyyupcankat.gallerist.dto.DtoAccountIU;
import com.eyyupcankat.gallerist.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountControllerImpl extends BaseController implements IAccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/save")
    @Override
    public ControllerResponse<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
        return ok(accountService.saveAccount(dtoAccountIU));
    }
}
