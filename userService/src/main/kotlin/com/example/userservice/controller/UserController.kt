package com.example.userservice.controller

import com.example.userservice.Mapper.ModelMapper
import com.example.userservice.VO.Greeting
import com.example.userservice.VO.RequestUser
import com.example.userservice.dto.UserDto
import com.example.userservice.service.UserService
import lombok.RequiredArgsConstructor
import org.slf4j.LoggerFactory


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class UserController (private val greeting: Greeting, private val userService: UserService){
    val log = LoggerFactory.getLogger(UserController::class.java)
//    @PostMapping
//    fun createUser(@RequestBody user:RequestUser):String{
//
////        val userDto = ModelMapper().mapper<RequestUser, UserDto>(user)
////        userService.createUser(userDto)
//        return "Create User method is Called"
//    }

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