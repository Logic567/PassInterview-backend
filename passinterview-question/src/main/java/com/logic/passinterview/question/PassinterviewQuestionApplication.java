package com.logic.passinterview.question;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCaching//开启 Spring Cache 注解
@EnableFeignClients(basePackages = "com.logic.passinterview.question.feign")
@SpringBootApplication
@MapperScan("com/logic/passinterview/question/dao")
@EnableDiscoveryClient
public class PassinterviewQuestionApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassinterviewQuestionApplication.class, args);
    }

}
