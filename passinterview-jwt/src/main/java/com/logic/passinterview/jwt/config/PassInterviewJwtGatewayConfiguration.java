package com.logic.passinterview.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Jwt 配置类
 */
@Configuration
public class PassInterviewJwtGatewayConfiguration {

    /**
     * 用户注册的密码也是经过 BCryptPasswordEncoder.encode 加密后存放到数据库
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
