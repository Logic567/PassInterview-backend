package com.logic.passinterview.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com/logic/passinterview/study/dao")
@EnableDiscoveryClient
public class PassinterviewStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassinterviewStudyApplication.class, args);
    }

}
