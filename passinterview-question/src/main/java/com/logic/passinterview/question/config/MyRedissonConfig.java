package com.logic.passinterview.question.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 单节点 Redis 的配置
 */
@Configuration
public class MyRedissonConfig {

    /**
     * 对 Redisson 的使用都是通过 RedissonClient 对象
     * @return
     */
    @Bean(destroyMethod = "shutdown")//服务停止后调用 shutdown 方法
    public RedissonClient redisson(){
        //1.创建配置
        Config config = new Config();
        //集群模式
//         config.useClusterServers().addNodeAddress("127.0.0.1:7004", "127.0.0.1:7001");
        //2.根据 config 创建出 RedissonClient 示例
        config.useSingleServer().setAddress("redis://10.119.197.214:6379");
        return Redisson.create(config);
    }
}
