package com.logic.passinterview.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.logic.passinterview"},exclude = {DataSourceAutoConfiguration.class})
public class PassinterviewGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassinterviewGatewayApplication.class, args);
    }

}
