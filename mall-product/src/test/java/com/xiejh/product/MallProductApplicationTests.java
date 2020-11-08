package com.xiejh.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiejh.product.entity.BrandEntity;
import com.xiejh.product.service.BrandService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MallProductApplicationTests {

    @Autowired
    BrandService brandService;
    @Autowired
    StringRedisTemplate  stringRedisTemplate;

    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("");
        brandEntity.setName("小米");
        brandService.save(brandEntity);
        System.out.println("保存成功");
    }

    @Test
    void testRedis(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        List<User> list = new ArrayList<>();
        list.add(new User("张三", 12));
        list.add(new User("李四", 18));
        ops.set("users", JSON.toJSONString(list));
        String users = ops.get("users");
        List<User> users1 = JSON.parseObject(users, new TypeReference<List<User>>() {});
        System.out.println(users1);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class User{
        String name;
        int age;
    }

}
