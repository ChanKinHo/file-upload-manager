package com.house.dubbo.service.impl;

import com.house.dubbo.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String msg) {
        return msg;
    }
}
