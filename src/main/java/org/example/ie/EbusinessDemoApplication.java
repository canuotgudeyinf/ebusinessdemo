package org.example.ie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"org.example.ie.mapper"})
public class EbusinessDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbusinessDemoApplication.class, args);
    }

}
