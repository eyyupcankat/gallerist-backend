package com.eyyupcankat.gallerist.controller.impl;

import com.eyyupcankat.gallerist.controller.BaseController;
import com.eyyupcankat.gallerist.controller.ControllerResponse;
import com.eyyupcankat.gallerist.controller.ICurrencyRatesController;
import com.eyyupcankat.gallerist.dto.CurrencyRatesResponse;
import com.eyyupcankat.gallerist.service.impl.CurrencyRatesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-rates")
public class CurrencyRatesControllerImpl extends BaseController implements ICurrencyRatesController {

    @Autowired
    private CurrencyRatesServiceImpl currencyRatesService;

    @GetMapping("/get")
    @Override
    public ControllerResponse<CurrencyRatesResponse> getCurrencyRates(@RequestParam(name = "startDate") String startDate,@RequestParam(name = "endDate") String endDate) {
        return ok(currencyRatesService.getCurrencyRates(startDate, endDate));
    }
}
