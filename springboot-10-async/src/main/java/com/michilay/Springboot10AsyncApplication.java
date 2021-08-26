package com.michilay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableAsync
@EnableScheduling
@SpringBootApplication
public class Springboot10AsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot10AsyncApplication.class, args);
    }

}
