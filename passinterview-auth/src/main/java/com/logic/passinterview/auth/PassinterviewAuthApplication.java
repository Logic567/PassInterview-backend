package com.logic.passinterview.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.logic.passinterview"})
public class PassinterviewAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassinterviewAuthApplication.class, args);
    }

}
