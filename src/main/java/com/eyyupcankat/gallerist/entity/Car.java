package com.eyyupcankat.gallerist.entity;

import com.eyyupcankat.gallerist.enums.CarStatusType;
import com.eyyupcankat.gallerist.enums.CurrencyType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity {

    private String plaka;

    private String brand;

    private String model;

    private Integer productionYear;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    private BigDecimal damagePrice;

    @Enumerated(EnumType.STRING)
    private CarStatusType carStatusType;


}
