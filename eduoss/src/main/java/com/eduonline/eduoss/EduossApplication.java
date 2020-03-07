package com.eduonline.eduoss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EduossApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduossApplication.class, args);
    }

}
