package com.example.userservice.service

import com.example.userservice.dto.UserDto

import com.example.userservice.entity.UserEntity
import com.example.userservice.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.security.core.userdetails.UserDetailsService

import org.springframework.stereotype.Service
import java.util.*


interface UserService{
    fun createUser(userDto: UserDto):UserDto
    fun getUserByAll():Iterable<UserEntity>
    fun getUserByUserId(userId:String):UserDto?
}