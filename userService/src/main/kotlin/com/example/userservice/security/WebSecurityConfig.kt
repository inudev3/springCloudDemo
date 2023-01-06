package com.example.userservice.security

import com.example.userservice.repository.UserRepository

import com.example.userservice.service.UserService
import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.IpAddressMatcher
import java.util.function.Supplier


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
class WebSecurityConfig(val userService: UserService, val passwordEncoder: BCryptPasswordEncoder, val env:Environment) {


    @Bean
    fun securityFilterChain(http: HttpSecurity,authenticationConfiguration: AuthenticationConfiguration): SecurityFilterChain {


        http.csrf()
            .disable()
//            .authorizeHttpRequests().requestMatchers("/users/**").permitAll()
            .authorizeHttpRequests().requestMatchers("/**").permitAll()
            .and()

            .addFilter(authenticationFilter(authenticationConfiguration.authenticationManager))
            .headers().frameOptions().disable()

        return http.build()

    }



    fun authenticationFilter(authenticationManager: AuthenticationManager):UsernamePasswordAuthenticationFilter {

        return AuthenticationFilter(userService,env).also { it.setAuthenticationManager(authenticationManager) }
    }

    private fun hasIpAddress(ipAddress: String): AuthorizationManager<RequestAuthorizationContext>? {
        val ipAddressMatcher = IpAddressMatcher(ipAddress)
        return AuthorizationManager { authentication: Supplier<Authentication?>?, context: RequestAuthorizationContext ->
            val request: HttpServletRequest = context.request
            AuthorizationDecision(ipAddressMatcher.matches(request))
        }
    }



}