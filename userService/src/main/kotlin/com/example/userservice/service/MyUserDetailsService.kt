package com.example.userservice.service

import com.example.userservice.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MyUserDetailsService(val userRepository: UserRepository, val passwordEncoder:PasswordEncoder) :UserDetailsService{
    private fun userNotFound(message:String?="User Not Found"){
        throw UsernameNotFoundException(message)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val userEntity = userRepository.findByEmail(username!!)
        userEntity?: userNotFound()
        return User(userEntity?.email,userEntity?.encryptedPwd, true,true,true,true,ArrayList())
    }
}