package com.eyyupcankat.gallerist.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class DtoCustomerIU {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String tckn;

    @NotNull
    private Date birthDate;

    @NotNull
    private Long addressId;

    @NotNull
    private Long accountId;

}
