package com.example.gateway

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig {
//    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes().route {
            it.path("/first-service/**").filters { f ->
                f.addRequestHeader("first-request", "first-request-header")
                    .addResponseHeader("first-response", "first-response-header")
            }.uri("lb://MY-FIRST-SERVICE")
        }.route {
            it.path("/second-service/**").filters { f ->
                f.addRequestHeader("second-request", "second-request-header")
                    .addResponseHeader("second-response", "second-response-header")
            }.uri("lb://MY-SECOND-SERVICE")
        }.route{
            it.path("/user-service/**").uri("http://localhost:8010/")
        }  .build()

    }
}