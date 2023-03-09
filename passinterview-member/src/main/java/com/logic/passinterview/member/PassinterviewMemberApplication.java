package com.logic.passinterview.member;

import com.logic.passinterview.common.config.WebMvcConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"com.logic.passinterview"})//扫描其他模块下的组件
@MapperScan("com/logic/passinterview/member/dao")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.logic.passinterview.member.feign")
@Import({WebMvcConfig.class})
public class PassinterviewMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassinterviewMemberApplication.class, args);
    }

}
