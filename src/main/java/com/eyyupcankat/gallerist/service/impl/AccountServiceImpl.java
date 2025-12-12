package com.eyyupcankat.gallerist.service.impl;

import com.eyyupcankat.gallerist.dto.DtoAccount;
import com.eyyupcankat.gallerist.dto.DtoAccountIU;
import com.eyyupcankat.gallerist.entity.Account;
import com.eyyupcankat.gallerist.repository.AccountRepository;
import com.eyyupcankat.gallerist.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;


    private Account createAccount(DtoAccountIU dtoAccountIU) {
        Account account = new Account();
        BeanUtils.copyProperties(dtoAccountIU, account);
        account.setCreateTime(new Date());
        return account;
    }

    @Override
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
        DtoAccount dtoAccount = new DtoAccount();

        Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));

        BeanUtils.copyProperties(savedAccount, dtoAccount);

        return dtoAccount;
    }
}
