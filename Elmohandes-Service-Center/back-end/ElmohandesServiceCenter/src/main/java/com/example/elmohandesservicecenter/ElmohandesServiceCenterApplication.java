package com.example.elmohandesservicecenter;

import com.example.elmohandesservicecenter.sittings.TokenConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TokenConfig.class)
@ConfigurationPropertiesScan

public class ElmohandesServiceCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElmohandesServiceCenterApplication.class, args);
    }

}
