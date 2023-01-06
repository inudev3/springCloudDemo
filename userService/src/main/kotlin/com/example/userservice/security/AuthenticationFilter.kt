package com.example.userservice.security

import com.example.userservice.VO.RequestLogin
import com.example.userservice.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.lang.RuntimeException



class AuthenticationFilter (val userService: UserService, val env:Environment): UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        try {
            val creds = ObjectMapper().readValue(request?.inputStream, RequestLogin::class.java)
            return UsernamePasswordAuthenticationToken(creds.email, creds.pwd, ArrayList()).let(
                authenticationManager::authenticate
            )

        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val user =(authResult?.principal as? User) ?: throw UsernameNotFoundException("User Not Found")
        val userDetails =  userService.getUserDetailsByEmail(user.username)
    }
}