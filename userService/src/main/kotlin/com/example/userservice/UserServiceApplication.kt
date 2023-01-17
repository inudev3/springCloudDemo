package com.example.userservice


import com.example.userservice.Mapper.ModelMapper
import feign.Logger
import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
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

	@Bean
	fun feignLoggerLevel():Logger.Level{
		return Logger.Level.FULL
	}

	@Bean
	fun timedAspect(registry: MeterRegistry): TimedAspect {
		return TimedAspect(registry)
	}

//	@Bean
//	@LoadBalanced
//	fun getRestTemplate():RestTemplate{
//		return RestTemplate()
//	}
}
fun main(args: Array<String>) {
	runApplication<UserServiceApplication>(*args)
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class NoArg

