package com.yflash.tech.apigateway.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        Function<PredicateSpec, Buildable<Route>> routeFunction = predicateSpec -> predicateSpec.path("/get")
                .filters(gatewayFilterSpec -> gatewayFilterSpec
                        .addRequestHeader("Custom-Header", "Custom Value")
                        .addRequestParameter("Custom-Param", "Param-Value"))
                .uri("http://httpbin.org:80");
        return builder.routes()
                .route(routeFunction)
                .build();
    }

}
