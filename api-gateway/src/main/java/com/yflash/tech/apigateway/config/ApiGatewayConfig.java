package com.yflash.tech.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(predicateSpec -> predicateSpec.path("/get")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                .addRequestHeader("Custom-Header", "Custom Value")
                                .addRequestParameter("Custom-Param", "Param-Value"))
                        .uri("http://httpbin.org:80"))
                .route(predicateSpec -> predicateSpec.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))                                         // talks to the eureka server to fetch the registered instances and load balances the request between them
                .route(predicateSpec -> predicateSpec.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion-service"))
                .route(predicateSpec -> predicateSpec.path("/currency-conversion-custom/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath(
                                "/currency-conversion-custom/(?<segment>.*)",
                                "/currency-conversion-feign/${segment}"
                        ))                                                                      // anything which comes after currency-conversion-custom is stored in segment variable
                                                                                                // and the same is attached after currency-conversion-feign/ via Spring Expression Language (SpEL)
                        .uri("lb://currency-conversion-service"))                               // redirecting internally to some existing URI
                .build();
    }

}
