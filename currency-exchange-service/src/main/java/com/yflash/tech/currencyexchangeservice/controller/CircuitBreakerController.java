package com.yflash.tech.currencyexchangeservice.controller;

import io.github.resilience4j.retry.annotation.Retry;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private static final Logger LOGGER = LogManager.getLogger(CircuitBreakerController.class);

    @GetMapping("sample-api")
    @Retry(name = "sample-api")
    public String sampleApi(HttpServletRequest request) {
        LOGGER.info("Intercepted request --> {}", request.getRequestURI());
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(
                "http:localhost:8080/dummy-api",
                HttpMethod.GET,
                null,
                String.class);
        return responseEntity.getBody();
    }

}
