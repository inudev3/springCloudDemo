package com.example.gateway

import com.example.gateway.filter.AuthorizationFilter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod

@Configuration
class FilterConfig {
    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder, authorizationFilter: AuthorizationFilter): RouteLocator {
        return builder.routes().route{
            it.path("/user-service/login").and().method(HttpMethod.POST).filters {
                it.rewritePath("/user-service/(?<segment>.*)", "/\${segment}").removeRequestHeader("Cookie")
            }.uri("lb://USER-SERVICE")
        } .route{
            it.path("/user-service/users").and().method(HttpMethod.POST).filters{
                it.rewritePath("/user-service/(?<segment>.*)", "/\${segment}").removeRequestHeader("Cookie")
            }.uri("lb://USER-SERVICE")
        }.route{
            it.path("/user-service/actuator/**").filters{
                it.rewritePath("/user-service/(?<segment>.*)", "/\${segment}").removeRequestHeader("Cookie")
            }.uri("lb://USER-SERVICE")
        }
            .route{
            it.path("/user-service/**").and().method(HttpMethod.GET).filters {
                it.rewritePath("/user-service/(?<segment>.*)", "/\${segment}").removeRequestHeader("Cookie")
                    .filter(authorizationFilter.apply(AuthorizationFilter.Config()))
            }.uri("lb://USER-SERVICE")
        }

            .route{
            it.path("/catalog-service/**").filters {
                it.rewritePath("/catalog-service/(?<segment>.*)", "/\${segment}")
            }.uri("lb://CATALOG-SERVICE")
        } .route{

            it.path("/order-service/**").filters {
                it.rewritePath("/order-service/(?<segment>.*)", "/\${segment}")
            }.uri("lb://ORDER-SERVICE")

        }.build()

    }
}