package com.logic.passinterview.question;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/logic/passinterview/question/dao")
public class PassinterviewQuestionApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassinterviewQuestionApplication.class, args);
    }

}
