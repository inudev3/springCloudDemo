package com.example.orderservice

import com.example.orderservice.Mapper.ModelMapper
import org.bouncycastle.math.raw.Mod
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableDiscoveryClient
class OrderServiceApplication{
	@Bean
	fun mapper():ModelMapper{
		return ModelMapper()
	}
}

fun main(args: Array<String>) {
	runApplication<OrderServiceApplication>(*args)
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class NoArg