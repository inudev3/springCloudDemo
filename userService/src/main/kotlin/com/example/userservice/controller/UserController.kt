package com.example.userservice.controller

import com.example.userservice.Mapper.ModelMapper
import com.example.userservice.VO.Greeting
import com.example.userservice.VO.RequestUser
import com.example.userservice.dto.UserDto
import com.example.userservice.VO.ResponseUser
import com.example.userservice.entity.UserEntity
import com.example.userservice.service.UserService
import org.slf4j.LoggerFactory


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
class UserController (private val mapper:ModelMapper,private val greeting: Greeting, private val userService: UserService){
    val log = LoggerFactory.getLogger(UserController::class.java)
    @PostMapping("/users")
    fun createUser(@RequestBody user:RequestUser):String{

        val userDto =mapper.mapper<RequestUser, UserDto>(user)
        userService.createUser(userDto)
        return "Create User method is Called"
    }
    @GetMapping("/users")
    fun getUsers():ResponseEntity<List<ResponseUser>> =userService.getUserByAll().map{
            mapper.mapper<UserEntity, ResponseUser>(it)
        }.let{ ResponseEntity.status(HttpStatus.OK).body(it)}

    @GetMapping("/users/{userId}")
    fun getUser(@PathVariable("userId") userId:String):ResponseEntity<ResponseUser> =
        userService.getUserByUserId(userId)?.let { mapper.mapper<UserDto, ResponseUser>(it) }.let{
            ResponseEntity.status(HttpStatus.OK).body(it)
        }

    @GetMapping("/health-check")
    fun status():String{
        log.info("도착!!")
        return "health Check"
    }

    @GetMapping("/welcome")
    fun welcome():String?{
        return greeting.message
    }


}