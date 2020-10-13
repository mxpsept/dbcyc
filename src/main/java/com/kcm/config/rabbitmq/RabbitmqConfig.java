package com.kcm.config.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * rabbitmq配置类
 *
 * @author lucky
 * @date 2020/8/18
 **/
@Slf4j
@Configuration
public class RabbitmqConfig {

    /***
     * rabbitmq消息模板配置
     *
     * @author lucky
     * @date 2020/8/19
     * @param connectionFactory 连接池
     * @return RabbitTemplate
     **/
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //设置开启Mandatory,才能触发回调函数，无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setMessageConverter(new RabbitmqFastJsonConverter());
        // 消息是否成功发送到Exchange
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息成功发送到Exchange!");
            } else {
                log.error("消息发送到Exchange失败,{},cause->{}", correlationData, cause);
            }
        });
        // 消息是否从Exchange路由到Queue,失败时回调
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) ->
                log.error("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}",
                        exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }

    /**
     * 封装了对 RabbitMQ 的管理操作
     *
     * @author lucky
     * @date 2020/8/19
     * @param connectionFactory ConnectionFactory
     * @return RabbitAdmin
     **/
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

}
