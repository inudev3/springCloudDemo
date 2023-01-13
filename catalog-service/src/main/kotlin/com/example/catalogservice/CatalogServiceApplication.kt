package com.example.catalogservice

import com.example.catalogservice.Mapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class CatalogServiceApplication{
	@Bean
	fun modelMapper():ModelMapper{
		return ModelMapper()
	}
}

fun main(args: Array<String>) {
	runApplication<CatalogServiceApplication>(*args)
}
inline fun <reified T> T.logger(): Logger {
	return LoggerFactory.getLogger(T::class.java)
}