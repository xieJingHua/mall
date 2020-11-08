package com.xiejh.product.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redis配置
 * @author xiejh
 * @Date 2020/11/8 20:46
 **/
@Configuration
public class RedisConfig {

    @Bean
    public RedissonClient redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://47.115.76.70:6379");
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
