package com.example.secondservice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
@Slf4j
@RequiredArgsConstructor
public class SecondServiceController {
    private final Environment env;
    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request){
        log.info("server port:{}", request.getServerPort());
        return String.format("Welcome. this is from PORT %s", env.getProperty("local.server.port"));
    }
    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header){
        log.info(header);
        return "hello second service!";
    }

}
