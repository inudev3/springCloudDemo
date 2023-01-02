package com.example.userservice.security

import com.example.userservice.Mapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.WebSecurityConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebSecurity
@Configuration
class WebSecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity):SecurityFilterChain{
        http.csrf()
            .disable()
            .authorizeHttpRequests().requestMatchers("/users/**").permitAll()
            .and()
            .headers().frameOptions().disable()

        return http.build()

    }
    @Bean
    fun bcryptPasswordEncorder():PasswordEncoder{
        return BCryptPasswordEncoder()
    }

}