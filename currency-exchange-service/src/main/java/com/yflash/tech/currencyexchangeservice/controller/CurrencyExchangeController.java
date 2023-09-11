package com.yflash.tech.currencyexchangeservice.controller;

import com.yflash.tech.currencyexchangeservice.entity.CurrencyExchange;
import com.yflash.tech.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{sourceCurrency}/to/{targetCurrency}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String sourceCurrency, @PathVariable String targetCurrency) {
        String port = environment.getProperty("local.server.port");
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(sourceCurrency, targetCurrency);
        if(currencyExchange == null) {
            throw new RuntimeException("Unable to find data for " + sourceCurrency + " to " + targetCurrency);
        }
        return currencyExchange;
    }

}
