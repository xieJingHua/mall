package com.xiejh.order;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.AMQP;
import com.xiejh.order.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitMqTest {

    @Autowired
    AmqpAdmin amqpAdmin;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void createExchange(){
        DirectExchange exchange = new DirectExchange("java-exchange",true,false);
        amqpAdmin.declareExchange(exchange);
        System.out.println("交换机创建完成");
    }

    @Test
    public void createQueue(){
        Queue queue = new Queue("java-queue",true);
        amqpAdmin.declareQueue(queue);
        System.out.println("队列创建完成创建完成");
    }

    @Test
    public void bindQueue(){
        createExchange();
        createQueue();
        /**
         * 对比web端的绑定，猜参数
         * String destination,  目的地  队列名或者交换机名
         * DestinationType destinationType,  目的地类型  队列或交换机
         * String exchange, 交换机
         * String routingKey, 路由键
         * @Nullable Map<String, Object> arguments
         */
        Binding binding = new Binding("java-queue", Binding.DestinationType.QUEUE,
                "java-exchange","java-queue",null);
        amqpAdmin.declareBinding(binding);
        System.out.println("绑定完成");
    }

    @Test
    public void sendMessage(){
        for(int i=0;i<10;i++){
            OrderEntity order = new OrderEntity();
            order.setMemberId(Long.valueOf(i));
            order.setOrderSn("订单号"+i);
            order.setBillContent("哈哈哈");
            rabbitTemplate.convertAndSend("java-exchange","java-queue",order);
            System.out.println("保存成功");
        }
    }
}
