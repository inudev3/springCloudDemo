package com.example.gateway.filter

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CustomFilter: AbstractGatewayFilterFactory<CustomFilter.Config>(Config::class.java) {
    class Config{}//설정 정보 구현
    val log = logger()
    override fun apply(config: Config?): GatewayFilter {
        return GatewayFilter{ exchange,chain->
            val request = exchange.request
            val response = exchange.response
            log.info("custom PRE filter: request uri ->{}", request.id)
            chain.filter(exchange).then(Mono.fromRunnable {
                log.info("custom POST filter: response code ->{}", response.statusCode)
            })
        }
    }

}
inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}
