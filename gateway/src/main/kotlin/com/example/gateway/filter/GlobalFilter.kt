package com.example.gateway.filter

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GlobalFilter: AbstractGatewayFilterFactory<GlobalFilter.Config>(Config::class.java) {
    val log = logger()
    override fun apply(config: Config?): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request = exchange.request
            val response = exchange.response
            log.info("Global Filter baseMessage : {}", config?.baseMessage)
            if (config?.preLogger==true) log.info("Global Start : {}", request.id)

            chain.filter(exchange).then(Mono.fromRunnable {
                log.info("Global Filter End: ${response.statusCode}")
            })
        }
    }

    class Config(val baseMessage:String, val preLogger:Boolean, val postLogger:Boolean)
}