package com.appintimedia.apifootball.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @RequestMapping(method = RequestMethod.GET, value = "/api/javainuse")
    public String sayHello() {
        return "Swagger Hello World";
    }
}
