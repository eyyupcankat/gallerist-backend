package com.eyyupcankat.gallerist.dto;

import lombok.Data;

@Data
public class DtoGalleristCar extends BaseDto{

    private DtoGallerist gallerist;

    private DtoCar car;
}
