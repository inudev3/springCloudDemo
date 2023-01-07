package com.example.orderservice

import com.example.orderservice.Mapper.ModelMapper
import org.bouncycastle.math.raw.Mod
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class OrderServiceApplication{
	@Bean
	fun mapper():ModelMapper{
		return ModelMapper()
	}
}

fun main(args: Array<String>) {
	runApplication<OrderServiceApplication>(*args)
}
