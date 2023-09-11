package com.yflash.tech.currencyconversionservice.service;

import com.yflash.tech.currencyconversionservice.model.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyConversionService {

    public CurrencyConversion getConversionData(String from, String to) {

        String url = "http://localhost:8000/currency-exchange/from/{from_currency}/to/{to_currency}";

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from_currency", from);
        uriVariables.put("to_currency", to);

        String uri = UriComponentsBuilder.fromHttpUrl(url)
                .buildAndExpand(uriVariables)
                .encode()
                .toUriString();

        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                null,
                CurrencyConversion.class
        );

        CurrencyConversion currencyConversion = null;

        if(responseEntity.hasBody())
            currencyConversion = responseEntity.getBody();

        return currencyConversion;
    }

}
