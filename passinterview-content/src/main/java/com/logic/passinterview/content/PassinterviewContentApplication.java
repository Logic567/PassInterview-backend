package com.logic.passinterview.content;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/logic/passinterview/content/dao")
public class PassinterviewContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassinterviewContentApplication.class, args);
    }

}
