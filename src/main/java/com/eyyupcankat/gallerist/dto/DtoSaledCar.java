package com.eyyupcankat.gallerist.dto;

import lombok.Data;

@Data
public class DtoSaledCar extends BaseDto {

    private DtoGallerist gallerist;

    private DtoCar car;

    private DtoCustomer customer;

}
