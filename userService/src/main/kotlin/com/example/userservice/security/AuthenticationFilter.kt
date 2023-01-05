package com.example.userservice.security

import com.example.userservice.VO.RequestLogin
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.lang.RuntimeException


class AuthenticationFilter : UsernamePasswordAuthenticationFilter() {
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        try {
            val creds = ObjectMapper().readValue(request?.inputStream, RequestLogin::class.java)
            return UsernamePasswordAuthenticationToken(creds.email, creds.password, ArrayList()).let(
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
        super.successfulAuthentication(request, response, chain, authResult)
    }
}