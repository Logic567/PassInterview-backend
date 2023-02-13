package com.logic.passinterview.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com/logic/passinterview/member/dao")
@EnableDiscoveryClient
public class PassinterviewMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassinterviewMemberApplication.class, args);
    }

}
