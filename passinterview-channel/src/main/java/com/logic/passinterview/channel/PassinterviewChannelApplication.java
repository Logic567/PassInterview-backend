package com.logic.passinterview.channel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/logic/passinterview/channel/dao")
public class PassinterviewChannelApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassinterviewChannelApplication.class, args);
    }

}
