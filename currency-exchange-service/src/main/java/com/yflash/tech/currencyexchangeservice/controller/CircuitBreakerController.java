package com.yflash.tech.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class CircuitBreakerController {

    private static final Logger LOGGER = LogManager.getLogger(CircuitBreakerController.class);

//    @GetMapping("sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "retryFailureResponse")
//    public String sampleApi(HttpServletRequest request) {
//        LOGGER.info("Intercepted request --> {}", request.getRequestURI());
//        ResponseEntity<String> responseEntity = new RestTemplate().exchange(
//                "http:localhost:8080/dummy-api",
//                HttpMethod.GET,
//                null,
//                String.class);
//        return responseEntity.getBody();
//    }

//    @GetMapping("sample-api")
//    @CircuitBreaker(name = "sample-api", fallbackMethod = "retryFailureResponse")
//    public String sampleApi(HttpServletRequest request) {
//        LOGGER.info("Intercepted request --> {}", request.getRequestURI());
//        ResponseEntity<String> responseEntity = new RestTemplate().exchange(
//                "http:localhost:8080/dummy-api",
//                HttpMethod.GET,
//                null,
//                String.class);
//        return responseEntity.getBody();
//    }

//    @GetMapping("sample-api")
//    @RateLimiter(name = "sample-api-rate-limiter", fallbackMethod = "retryFailureResponse")
//    public String sampleApi(HttpServletRequest request) {
//        LOGGER.info("Intercepted request --> {}", request.getRequestURI());
//        return "sample-api";
//    }

    @GetMapping("sample-api")
    @Bulkhead(name = "sample-api-bulkhead", fallbackMethod = "retryFailureResponse")
    public String sampleApi(HttpServletRequest request) {
        LOGGER.info("Intercepted request --> {}", request.getRequestURI());
        return "sample-api";
    }

    public String retryFailureResponse(Exception e) {
        LOGGER.info("fallback method for generic exception");
        return "generic exception fallback";
    }

    public String retryFailureResponse(IOException e) {
        LOGGER.info("fallback method for IOException");
        return "IOException fallback";
    }

}
