package com.logic.passinterview.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/logic/passinterview/study/dao")
public class PassinterviewStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassinterviewStudyApplication.class, args);
    }

}
