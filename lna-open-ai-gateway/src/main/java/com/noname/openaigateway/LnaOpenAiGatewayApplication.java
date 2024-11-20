package com.noname.openaigateway;

import com.noname.openaigateway.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class LnaOpenAiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LnaOpenAiGatewayApplication.class, args);
    }

}