package com.taxi.partner.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local-discovery")
@Configuration
public class LoadBalancedRoutesConfig {

    @Bean
    public RouteLocator loadBalancedRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/api/v1/applications*", "/api/v1/applications/*")
                        .uri("lb://registration-service")
                        .id("registration-service"))
                .route(r -> r.path("/api/v1/drivers/**")
                        .uri("lb://review-service")
                        .id("review-service"))
                .build();
    }

}
