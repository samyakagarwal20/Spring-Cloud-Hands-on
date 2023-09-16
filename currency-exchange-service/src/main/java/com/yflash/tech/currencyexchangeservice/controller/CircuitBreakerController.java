package com.yflash.tech.currencyexchangeservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {

    private static final Logger LOGGER = LogManager.getLogger(CircuitBreakerController.class);

    @GetMapping("sample-api")
    public String sampleApi(HttpServletRequest request) {
        LOGGER.info("Intercepted request --> {}", request.getRequestURI());
        return "Sample API";
    }

}
