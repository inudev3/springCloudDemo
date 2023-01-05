package com.example.userservice.security

import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.security.web.util.matcher.IpAddressMatcher
import java.util.function.Supplier


@EnableWebSecurity
@Configuration

class WebSecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity):SecurityFilterChain{
        http.csrf()
            .disable()
//            .authorizeHttpRequests().requestMatchers("/users/**").permitAll()
            .authorizeHttpRequests{auth->
                auth.requestMatchers("/**").access(hasIpAddress("192.168.0.1"))
            }
            .addFilter(AuthenticationFilter())
            .headers().frameOptions().disable()

        return http.build()

    }

    @Bean //updated version of AuthenticationManagerBuilder, authenticationmanager is already created with userdetailService bean and passwordEncoder bean
    fun authenticationManager(authenticationConfig:AuthenticationConfiguration):AuthenticationManager =authenticationConfig.authenticationManager


    private fun hasIpAddress(ipAddress: String): AuthorizationManager<RequestAuthorizationContext>? {
        val ipAddressMatcher = IpAddressMatcher(ipAddress)
        return AuthorizationManager { authentication: Supplier<Authentication?>?, context: RequestAuthorizationContext ->
            val request: HttpServletRequest = context.request
            AuthorizationDecision(ipAddressMatcher.matches(request))
        }
    }
    @Bean
    fun passwordEncoder():BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }

}