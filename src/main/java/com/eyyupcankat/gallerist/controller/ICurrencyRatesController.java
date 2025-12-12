package com.eyyupcankat.gallerist.controller;

import com.eyyupcankat.gallerist.dto.CurrencyRatesResponse;

public interface ICurrencyRatesController {

    public ControllerResponse<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);
}
