package com.gm2.dev;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p
                        .path("/customer-account/**")
                        .uri("lb://account-service"))
                .route(p -> p
                        .path("/transaction/**")
                        .uri("lb://transaction-service"))
                .build();
    }
}
