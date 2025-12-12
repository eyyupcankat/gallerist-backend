package com.eyyupcankat.gallerist.service.impl;

import com.eyyupcankat.gallerist.dto.CurrencyRatesResponse;
import com.eyyupcankat.gallerist.exception.BaseException;
import com.eyyupcankat.gallerist.exception.ErrorMessage;
import com.eyyupcankat.gallerist.exception.MessageType;
import com.eyyupcankat.gallerist.service.ICurrencyRatesService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {



    @Override
    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {

        String rootUrl = "https://evds2.tcmb.gov.tr/service/evds/";
        String series = "TP.DK.USD.A";
        String type = "json";

        String endPoint = rootUrl+"series="+series+"&startDate="+startDate+"&endDate="+endDate+"&type="+type;
        //https://evds2.tcmb.gov.tr/service/evds/series=TP.DK.USD.A&startDate=10-10-2025&endDate=10-10-2025&type=json

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key","l6xufhPgF0");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endPoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrencyRatesResponse>() {});
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURRED,e.getMessage()));
        }
         return null;
    }


}
