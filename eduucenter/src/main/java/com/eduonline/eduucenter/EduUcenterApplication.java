package com.eduonline.eduucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EduUcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduUcenterApplication.class);
    }
}
