package com.eduonline.edugateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy //作为网关启动
@EnableEurekaClient
public class EduGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduGatewayApplication.class);
    }
}
