package com.xiejh.product.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xiejh
 * @Date 2020/11/8 21:16
 **/
@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private RedissonClient redisson;

    @GetMapping("/testLock")
    public void testLock(){
        RLock lock = redisson.getLock("my-lock");
        System.out.println("尝试加锁");
        lock.lock();
        System.out.println("加锁成功");
        try{
           Thread.sleep(38000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally块,解锁");
            lock.unlock();
        }
    }

    @GetMapping("/readValue")
    public void readValue(){
        RReadWriteLock rwlock = redisson.getReadWriteLock("rwlock");
        RLock rLock = rwlock.readLock();
        rLock.lock();
        try {
            log.info("读锁加锁成功");
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rLock.unlock();
            log.info("读锁解锁成功");
        }
    }

    @GetMapping("/writeValue")
    public void writeValue(){
        RReadWriteLock rwlock = redisson.getReadWriteLock("rwlock");
        RLock rLock = rwlock.writeLock();
        rLock.lock();
        try {
            log.info("写锁加锁成功");
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rLock.unlock();
            log.info("写锁解锁成功");
        }
    }

    @GetMapping("/testCache")
    @Cacheable(value = "demo",key = "#root.methodName")
    public List<User> testRedis(){
        System.out.println("调用方法testRedis");
        List<User> list = new ArrayList<>();
        list.add(new User("张三", 12));
        list.add(new User("李四", 18));
       return list;
    }

    @CacheEvict(value = "demo",key = "'testRedis'")
    public void removeCache(){
        System.out.println("清除缓存");
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class User{
        String name;
        int age;
    }
}
