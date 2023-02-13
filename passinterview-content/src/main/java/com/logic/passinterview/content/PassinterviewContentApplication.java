package com.logic.passinterview.content;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com/logic/passinterview/content/dao")
@EnableDiscoveryClient
public class PassinterviewContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassinterviewContentApplication.class, args);
    }

}
