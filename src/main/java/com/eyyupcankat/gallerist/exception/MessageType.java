package com.eyyupcankat.gallerist.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004","KAYIT BULUNAMADI"),
    TOKEN_IS_EXPIRED("1005","TOKENİN SÜRESİ DOLMUŞTUR"),
    USERNAME_NOT_FOUND("1006","USERNAME BULUNAMADI"),
    USERNAME_OR_PASSWORD_INVALID("1007","USERNAME BULUNAMADI"),
    REFRESH_TOKEN_NOT_FOUND("1008","REFRESH TOKEN BULUNAMADI"),
    REFRESH_TOKEN_IS_EXPIRED("1009","REFRESH TOKENİN SÜRESİ DOLMUŞTUR"),
    CURRENCY_RATES_IS_OCCURRED("1010","CURRENCY RATES ALINAMADI"),
    CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1011","CUSTOMER AMOUNT NOT ENOUGH"),
    CAR_IS_NOT_SALABLE("1012","CAR NOT SALABLE"),
    GENERAL_EXCEPTION("9999","GENEL BİR HATA OLUŞTU");


    private String code;

    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
