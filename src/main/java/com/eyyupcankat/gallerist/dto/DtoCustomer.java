package com.eyyupcankat.gallerist.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DtoCustomer extends BaseDto{

    private String firstName;

    private String lastName;

    private String tckn;

    private Date birthDate;

    private DtoAddress DtoAddress;

    private DtoAccount DtoAccount;

}
