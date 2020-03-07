package com.eduonline.eduvideo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//排除数据源加载
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
public class EduVideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduVideoApplication.class, args);
    }
}
