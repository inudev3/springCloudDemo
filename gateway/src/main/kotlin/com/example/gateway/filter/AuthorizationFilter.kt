package com.example.gateway.filter

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.io.Encoder
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import org.apache.hc.core5.http.HttpHeaders

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


@Component
class AuthorizationFilter(val env:Environment) :AbstractGatewayFilterFactory<AuthorizationFilter.Config>(AuthorizationFilter.Config::class.java) {
    class Config

    val log = logger()
    override fun apply(config: Config?): GatewayFilter {
        return GatewayFilter{ exchange,chain->
            val (request,response) = exchange.request to exchange.response

            val token=request.headers[HttpHeaders.AUTHORIZATION]?.get(0)?.replace("Bearer", "") ?: return@GatewayFilter onError(exchange, "no authorizatio header", HttpStatus.UNAUTHORIZED)
            if(!token.isJwtValid()) return@GatewayFilter onError(exchange, "invalid gittoken", HttpStatus.UNAUTHORIZED)
            chain.filter(exchange)
        }
    }

    private fun onError(exchange: ServerWebExchange?, message: String, status: HttpStatus): Mono<Void>? {
        val response = exchange?.response ?: return Mono.error(RuntimeException())

        log.error(message)
        return response.setComplete()
    }
    private fun String.isJwtValid(): Boolean {

        val key =  env.getProperty("token.secret").let{
            Decoders.BASE64.decode(it).let{ bytes -> Keys.hmacShaKeyFor(bytes)}
        }
        val subject = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(this).body.subject ?:return false
        if(subject.isEmpty()) return false
        return true
    }


}


