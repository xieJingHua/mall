package com.xiejh.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbitmq.client.Channel;
import com.xiejh.common.utils.PageUtils;
import com.xiejh.common.utils.Query;
import com.xiejh.order.dao.OrderItemDao;
import com.xiejh.order.entity.OrderEntity;
import com.xiejh.order.entity.OrderItemEntity;
import com.xiejh.order.service.OrderItemService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;


@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderItemEntity> page = this.page(
                new Query<OrderItemEntity>().getPage(params),
                new QueryWrapper<OrderItemEntity>()
        );

        return new PageUtils(page);
    }

    @RabbitListener(queues = {"java-queue"})
    public void receiveMessage(Message message, OrderEntity order, Channel channel) throws InterruptedException {
        byte[] body = message.getBody();
        MessageProperties messageProperties = message.getMessageProperties();
        System.out.println("接收消息："+order.getOrderSn());
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //确认接收
            if(deliveryTag%2==0){
                channel.basicAck(deliveryTag,false);
            }else{
                //拒收
                channel.basicNack(deliveryTag,false,true);
            }
        } catch (IOException e) {
            //网络中断
            e.printStackTrace();
        }
    }
}