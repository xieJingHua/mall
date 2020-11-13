package com.xiejh.cart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiejh
 * @Date 2020/11/13 10:15
 **/
@RequestMapping("/demo")
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        Thread thread = Thread.currentThread();
        System.out.println("控制层："+thread.getId());
        return "hello world";
    }
}
