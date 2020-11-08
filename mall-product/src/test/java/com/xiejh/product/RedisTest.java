package com.xiejh.product;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiejh
 * @Date 2020/11/8 20:51
 **/
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testRedisson(){
        System.out.println(redissonClient.toString());
    }
}
