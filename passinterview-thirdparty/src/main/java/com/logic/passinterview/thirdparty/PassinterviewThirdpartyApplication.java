package com.logic.passinterview.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class PassinterviewThirdpartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassinterviewThirdpartyApplication.class, args);
    }

}
