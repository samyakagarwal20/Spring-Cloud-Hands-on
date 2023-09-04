package com.yflash.tech.currencyexchangeservice.controller;

import com.yflash.tech.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @GetMapping("/currency-exchange/from/{sourceCurrency}/to/{targetCurrency}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String sourceCurrency, @PathVariable String targetCurrency) {
        return new CurrencyExchange(1000L, sourceCurrency, targetCurrency, BigDecimal.TEN);
    }

}
