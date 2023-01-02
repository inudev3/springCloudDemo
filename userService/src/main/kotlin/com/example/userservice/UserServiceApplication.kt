package com.example.userservice

import com.example.userservice.Mapper.ModelMapper
import org.springframework.boot.Banner.Mode
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableDiscoveryClient
class UserServiceApplication{
	@Bean
	fun modelMapper():ModelMapper{
		return ModelMapper()
	}
}
fun main(args: Array<String>) {
	runApplication<UserServiceApplication>(*args)
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class NoArg