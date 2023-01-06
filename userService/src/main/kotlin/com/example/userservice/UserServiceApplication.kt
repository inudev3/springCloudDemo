package com.example.userservice

import com.example.userservice.Mapper.ModelMapper
import org.springframework.boot.Banner.Mode
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Conditional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
@EnableDiscoveryClient
class UserServiceApplication{
	@Bean
	fun modelMapper():ModelMapper{
		return ModelMapper()
	}
	@Bean
	fun passwordEncoder(): BCryptPasswordEncoder {
		return BCryptPasswordEncoder()
	}
	@Bean //updated version of AuthenticationManagerBuilder, authenticationmanager is already created with userdetailService bean and passwordEncoder bean
	@ConditionalOnMissingBean
	fun authenticationManager(authenticationConfig: AuthenticationConfiguration): AuthenticationManager =
		authenticationConfig.authenticationManager

}
fun main(args: Array<String>) {
	runApplication<UserServiceApplication>(*args)
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class NoArg