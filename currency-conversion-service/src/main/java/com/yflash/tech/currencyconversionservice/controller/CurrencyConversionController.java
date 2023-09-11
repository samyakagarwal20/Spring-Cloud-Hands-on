package com.yflash.tech.currencyconversionservice.controller;

import com.yflash.tech.currencyconversionservice.model.CurrencyConversion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @GetMapping("/currency-conversion/from/{from_currency}/to/{to_currency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable("from_currency") String sourceCurrency,
                                                          @PathVariable("to_currency") String targetCurrency,
                                                          @PathVariable("quantity") BigDecimal quantity) {
        return new CurrencyConversion(1000L, sourceCurrency, targetCurrency, quantity, BigDecimal.TEN, BigDecimal.ONE, "");
    }

}
