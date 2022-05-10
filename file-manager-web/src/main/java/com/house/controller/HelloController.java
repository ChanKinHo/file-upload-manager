package com.house.controller;

import com.house.demo.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @Resource
    HelloService helloService;

    @RequestMapping("/meet")
    @ResponseBody
    public String sayHello(String msg){

        return helloService.hello(msg);
    }
}
