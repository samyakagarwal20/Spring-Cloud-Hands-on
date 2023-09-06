package com.yflash.tech.currencyexchangeservice.controller;

import com.yflash.tech.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{sourceCurrency}/to/{targetCurrency}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String sourceCurrency, @PathVariable String targetCurrency) {
        String port = environment.getProperty("local.server.port");
        CurrencyExchange currencyExchange = new CurrencyExchange(1000L, sourceCurrency, targetCurrency, BigDecimal.TEN, port);
        return currencyExchange;
    }

}
