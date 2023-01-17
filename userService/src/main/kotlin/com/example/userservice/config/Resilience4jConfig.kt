package com.example.userservice.config

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import io.github.resilience4j.kotlin.bulkhead.BulkheadConfig
import io.github.resilience4j.kotlin.bulkhead.BulkheadRegistry
import io.github.resilience4j.kotlin.bulkhead.ThreadPoolBulkheadConfig
import io.github.resilience4j.kotlin.bulkhead.ThreadPoolBulkheadRegistry
import io.github.resilience4j.kotlin.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import io.github.resilience4j.timelimiter.TimeLimiterRegistry
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4jBulkheadProvider
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class Resilience4jConfig {
    @Bean
    fun globalCustomConfig(): Customizer<Resilience4JCircuitBreakerFactory> {
        val config = CircuitBreakerConfig.custom()
            .failureRateThreshold(4F)
            .waitDurationInOpenState(Duration.ofMillis(1000))
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(2)
            .build()
        val timelimit = TimeLimiterConfig.custom()
            .timeoutDuration(Duration.ofSeconds(4))
            .build()
        return Customizer { factory-> factory.configureDefault { id-> Resilience4JConfigBuilder(id).timeLimiterConfig(timelimit).circuitBreakerConfig(config).build() } }
    }
}