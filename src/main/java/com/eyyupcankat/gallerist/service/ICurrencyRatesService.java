package com.eyyupcankat.gallerist.service;

import com.eyyupcankat.gallerist.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {

    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate);

}
