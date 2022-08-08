package com.example.oath2sercurity;

import com.example.oath2sercurity.Config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class Oath2SercurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oath2SercurityApplication.class, args);
    }

}
