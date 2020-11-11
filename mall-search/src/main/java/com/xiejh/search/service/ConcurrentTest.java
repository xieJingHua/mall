package com.xiejh.search.service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiejh
 * @Date 2020/11/11 8:36
 **/
@Slf4j
public class ConcurrentTest {

    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            log.info("任务1");
//            return 1;
//        }, executor).applyToEitherAsync(CompletableFuture.supplyAsync(() -> {
//            log.info("任务2开始");
//            try {
//                Thread.sleep(3000);
//                log.info("任务2结束");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 2;
//        }), (a) -> {
//            log.info("消费");
//            System.out.println(a);
//            return "消费" + a;
//        });
//
//        log.info("最终："+future.get());

        CompletableFuture<String> futureCat = CompletableFuture.supplyAsync(() -> {
            log.info("查询商品分类");
            return "手机";
        },executor);
        CompletableFuture<String> futureAttr = CompletableFuture.supplyAsync(() -> {
            log.info("查询商品属性");
            return "黑色+256G";
        },executor);
        CompletableFuture<String> futureDesc = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("查询商品介绍");
            return "华为";
        },executor);
//        CompletableFuture<Void> future = CompletableFuture.allOf(futureCat, futureAttr, futureDesc);
        CompletableFuture<Object> future = CompletableFuture.anyOf(futureCat, futureAttr, futureDesc);
//        System.out.println("最终:"+futureCat.get()+","+futureAttr.get()+"."+futureDesc.get());
        System.out.println("最终："+future.get());
    }

}
