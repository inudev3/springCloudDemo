package com.example.firstservice

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/first-service")

class FirstServiceController @Autowired constructor(private val env:Environment) {
    val log = logger()

    @GetMapping("/welcome")
    fun welcome(request:HttpServletRequest):String{
        log.info("server port:{}", request.serverPort)

        return "Welcome. this is from PORT ${env.getProperty("local.server.port")}"
    }
    @GetMapping("/message")
    fun message(@RequestHeader("first-request") header:String):String{
        log.info(header)
        return "hello first service!"
    }
}
inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}