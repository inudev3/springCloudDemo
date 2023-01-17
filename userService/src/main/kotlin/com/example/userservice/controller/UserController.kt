package com.example.userservice.controller

import com.example.userservice.Mapper.ModelMapper

import com.example.userservice.VO.RequestUser
import com.example.userservice.dto.UserDto
import com.example.userservice.VO.ResponseUser
import com.example.userservice.entity.UserEntity
import com.example.userservice.service.UserService
import io.micrometer.core.annotation.Timed
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment


import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class UserController (private val mapper:ModelMapper,private val env:Environment,private val userService: UserService){
    val log = LoggerFactory.getLogger(UserController::class.java)
    @PostMapping("/users")
    fun createUser(@RequestBody user:RequestUser):ResponseEntity<ResponseUser>{

        val userDto =mapper.mapper<RequestUser, UserDto>(user)
        val result= userService.createUser(userDto).let{
            mapper.mapper<UserDto, ResponseUser>(it)
        }
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }
    @GetMapping("/users")
    fun getUsers():ResponseEntity<List<ResponseUser>> =userService.getUserByAll().map{
            mapper.mapper<UserEntity, ResponseUser>(it)
        }.let{ ResponseEntity.status(HttpStatus.OK).body(it)}

    @GetMapping("/users/{userId}")
    fun getUser(@PathVariable("userId") userId:String):ResponseEntity<ResponseUser> =
        userService.getUserByUserId(userId).let { mapper.mapper<UserDto, ResponseUser>(it) }.let{
            ResponseEntity.status(HttpStatus.OK).body(it)
        }

    @Timed(value = "users.status", longTask = true)
    @GetMapping("/health-check")
    fun status():String{

        return """
            current.profile=${env.getProperty("current.profile")}
            token.secret=${env.getProperty("token.secret")}
        """.trimIndent()
    }

    @Timed(value="users.welcome", longTask = true)
    @GetMapping("/welcome")
    fun welcome():String?{
        return "welcome"
    }


}