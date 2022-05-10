package com.house;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class FileUploadWorkerApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-provider.xml");
        context.start();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
