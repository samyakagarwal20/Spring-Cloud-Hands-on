package com.yflash.tech.currencyconversionservice.feignClients;

import com.yflash.tech.currencyconversionservice.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange", url = "localhost:8000")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{sourceCurrency}/to/{targetCurrency}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String sourceCurrency, @PathVariable String targetCurrency);

}
