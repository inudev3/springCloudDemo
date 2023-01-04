package com.example.gateway

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig {
    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes().route{
            it.path("/user-service/**").uri("lb://USER-SERVICE")
        } .route{
            it.path("/catalog-service/**").uri("lb://CATALOG-SERVICE")
        } .route{
            it.path("/order-service/**").uri("lb://ORDER-SERVICE")
        }.build()

    }
}