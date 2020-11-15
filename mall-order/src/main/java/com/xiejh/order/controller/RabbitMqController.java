package com.xiejh.order.controller;

import com.xiejh.order.entity.OrderEntity;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author xiejh
 * @Date 2020/11/13 23:03
 **/
@RequestMapping("/mq")
@RestController
public class RabbitMqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage")
    public void sendMessage(){
        for(int i=0;i<10;i++){
            OrderEntity order = new OrderEntity();
            order.setMemberId(Long.valueOf(i));
            order.setOrderSn("订单号"+i);
            order.setBillContent("哈哈哈");
            rabbitTemplate.convertAndSend("java-exchange","java-queue",order,
                    new CorrelationData(UUID.randomUUID().toString()));
            System.out.println("保存成功");
        }
    }
}
