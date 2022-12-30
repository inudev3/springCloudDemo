package com.example.gateway.filter

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class LoggingFilter :AbstractGatewayFilterFactory<LoggingFilter.Config>(Config::class.java){
    val log by lazy{logger()}
    override fun apply(config: Config?): GatewayFilter {
        return OrderedGatewayFilter(GatewayFilter { exchange, chain ->
            val request = exchange.request
            val response = exchange.response
            log.info("Logging Filter baseMessage : {}", config?.baseMessage)
            if (config?.preLogger==true) log.info("Logging Start : {}", request.id)

            chain.filter(exchange).then(Mono.fromRunnable {
                log.info("Logging Filter End: ${response.statusCode}")
            })},
            Ordered.HIGHEST_PRECEDENCE)
    }

    class Config(val baseMessage:String, val preLogger:Boolean, val postLogger:Boolean)
}