package com.eyyupcankat.gallerist.service.impl;

import com.eyyupcankat.gallerist.dto.DtoAccount;
import com.eyyupcankat.gallerist.dto.DtoAddress;
import com.eyyupcankat.gallerist.dto.DtoCustomer;
import com.eyyupcankat.gallerist.dto.DtoCustomerIU;
import com.eyyupcankat.gallerist.entity.Account;
import com.eyyupcankat.gallerist.entity.Address;
import com.eyyupcankat.gallerist.entity.Customer;
import com.eyyupcankat.gallerist.exception.BaseException;
import com.eyyupcankat.gallerist.exception.ErrorMessage;
import com.eyyupcankat.gallerist.exception.MessageType;
import com.eyyupcankat.gallerist.repository.AccountRepository;
import com.eyyupcankat.gallerist.repository.AddressRepository;
import com.eyyupcankat.gallerist.repository.CustomerRepository;
import com.eyyupcankat.gallerist.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {
        Optional<Address> optionalAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
        if (optionalAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAddressId().toString()));
        }

        Optional<Account> optionalAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
        if (optionalAccount.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAccountId().toString()));
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(dtoCustomerIU, customer);
        customer.setCreateTime(new Date());

        customer.setAccount(optionalAccount.get());
        customer.setAddress(optionalAddress.get());

        return customer;
    }

    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
        Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));

        DtoCustomer dtoCustomer = new DtoCustomer();
        BeanUtils.copyProperties(savedCustomer, dtoCustomer);

        DtoAddress  dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();
        BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);
        BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);

        dtoCustomer.setDtoAccount(dtoAccount);
        dtoCustomer.setDtoAddress(dtoAddress);

        return dtoCustomer;
    }

}
