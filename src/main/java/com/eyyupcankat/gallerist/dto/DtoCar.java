package com.eyyupcankat.gallerist.dto;

import com.eyyupcankat.gallerist.enums.CarStatusType;
import com.eyyupcankat.gallerist.enums.CurrencyType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DtoCar extends BaseDto {

    private String plaka;

    private String brand;

    private String model;

    private Integer productionYear;

    private BigDecimal price;

    private CurrencyType currencyType;

    private BigDecimal damagePrice;

    private CarStatusType carStatusType;
}
