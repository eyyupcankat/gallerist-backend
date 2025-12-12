package com.eyyupcankat.gallerist.entity;

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
public class Account extends BaseEntity{

    private String accountNo;

    private String iban;

    // parasal işlemler bigdecimal ile tutulurmuş
    private BigDecimal amount;

    // enum olduğu için enumerated kullanıyoruz ve
    // içine 0 veya 1 değil de string olarak değer
    // girmesi için enumtype.string veriyoruz
    // ordinal verseydik 0 || 1 verirdi
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;


}
