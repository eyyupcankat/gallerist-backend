package com.eyyupcankat.gallerist.service;

import com.eyyupcankat.gallerist.dto.DtoAccount;
import com.eyyupcankat.gallerist.dto.DtoAccountIU;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

}
