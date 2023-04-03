package com.logic.passinterview.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PassInterviewElasticsearchConfig {

    @Bean
    //给容器注册一个 RestHighLevelClient，用来操作 ES
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.245.129",9200,"http")));
    }
}
