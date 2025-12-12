package com.eyyupcankat.gallerist.dto;

import com.eyyupcankat.gallerist.enums.CarStatusType;
import com.eyyupcankat.gallerist.enums.CurrencyType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DtoCarIU{

    @NotEmpty
    private String plaka;

    @NotEmpty
    private String brand;

    @NotEmpty
    private String model;

    @NotNull
    private Integer productionYear;

    @NotNull
    private BigDecimal price;

    @NotNull
    private CurrencyType currencyType;

    @NotNull
    private BigDecimal damagePrice;

    @NotNull
    private CarStatusType carStatusType;
}
