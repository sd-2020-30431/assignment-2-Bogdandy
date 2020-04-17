package com.boot;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ComponentScan(basePackages= {"com.boot.controllers","com.boot.resolvers", "com.boot.repositories", "com.boot.entities"})
public class SpringBooter {

    public static void main(String[] args) {
        SpringApplication.run(SpringBooter.class, args);
    }
}