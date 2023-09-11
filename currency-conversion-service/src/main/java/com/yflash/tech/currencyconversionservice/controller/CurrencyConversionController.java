package com.yflash.tech.currencyconversionservice.controller;

import com.yflash.tech.currencyconversionservice.feignClients.CurrencyExchangeProxy;
import com.yflash.tech.currencyconversionservice.model.CurrencyConversion;
import com.yflash.tech.currencyconversionservice.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @Autowired
    CurrencyConversionService conversionService;

    @Autowired
    CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from_currency}/to/{to_currency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable("from_currency") String sourceCurrency,
                                                          @PathVariable("to_currency") String targetCurrency,
                                                          @PathVariable("quantity") BigDecimal quantity) {
        CurrencyConversion currencyConversion = conversionService.getConversionData(sourceCurrency, targetCurrency);
        return currencyConversion.setQuantity(quantity)
                .setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
    }

    @GetMapping("/currency-conversion-feign/from/{from_currency}/to/{to_currency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable("from_currency") String sourceCurrency,
                                                          @PathVariable("to_currency") String targetCurrency,
                                                          @PathVariable("quantity") BigDecimal quantity) {
        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(sourceCurrency, targetCurrency);
        return currencyConversion.setQuantity(quantity)
                .setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()))
                .setEnvironment(currencyConversion.getEnvironment() + " feign");
    }

}
