package com.kcm.modules.system.role.controller;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: lucky
 * @date: 2020/8/18
 * @description: 消息推送消费者
 **/
@Component
public class TestTopicCustomer {

    //定义mq中不存在的Queue，exchange和route key
    //支持自动声明绑定，声明之后自动监听队列的队列，
    //此时@RabbitListener注解的queue和bindings不能同时指定，否则报错
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "test.user", durable = "true"),//创建临时队列,durable设置为true,消息队列持久化
                    exchange = @Exchange(value = "testExchange",durable = "true",type = "topic"),
                    key = {"testUser"}
            )
    })
    public void userAddReceive(String message) {
        System.out.println("测试消费用户日志消息");
        System.out.println(message);
    }

    @RabbitListener(queues = "log.user.add")
    public void userLogMessage1(String message){
        System.out.println("消费用户新增消息");
        System.out.println(message);
    }
    @RabbitListener(queues = "log.user.update")
    public void userLogMessage2(String message){
        System.out.println("消费用户更新消息");
        System.out.println(message);
    }


}
