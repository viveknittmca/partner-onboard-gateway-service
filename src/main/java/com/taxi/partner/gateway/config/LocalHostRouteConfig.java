package com.taxi.partner.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!local-discovery")
@Configuration
public class LocalHostRouteConfig {

    @Bean
    public RouteLocator localHostRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/api/v1/applications*", "/api/v1/applications/*")
                        .uri("http://localhost:8080")
                        .id("registration-service"))
                .route(r -> r.path("/api/v1/drivers/**")
                        .uri("http://localhost:8081")
                        .id("review-service"))
                .build();
    }
}
