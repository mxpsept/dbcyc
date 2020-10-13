package com.kcm.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lucky
 * @date: 2020/8/18
 * @description: topic主题交换机
 **/
@Configuration
public class TopicRabbitConfig {

    public final static String LOG_USER_ADD = "log.user.add";
    public final static String LOG_USER_UPDATE = "log.user.update";
    public final static String LOG_USER_DELETE = "log.user.delete";
    /**
     * 系统发布消息队列
     */
    public static final String QUEUE_SYSTEM = "queue_system";
    /**
     * 系统反馈消息队列
     */
    public static final String QUEUE_SYSTEM_FEEDBACK = "queue_system_feedback";
    /**
     * 系统Exchange
     */
    public static final String EXCHANGE_TOPIC = "exchange_system";
    /**
     * 系统发布routingKey
     */
    public static final String ROUTING_KEY_SYSTEM = "routing_key_system";
    /**
     * 系统反馈routingKey
     */
    public static final String ROUTING_KEY_SYSTEM_FEEDBACK = "routing_key_system_feedback";


    /***
     * 用户新增操作日志队列
     * @author lucky
     * @date 2020/8/18
     * @return 用户日志
     **/
    @Bean
    public Queue userAddQueue() {
        return new Queue(TopicRabbitConfig.LOG_USER_ADD);
    }

    /***
     * 用户更新操作日志队列
     * @author lucky
     * @date 2020/8/18
     * @return 部门日志
     **/
    @Bean
    public Queue userUptQueue() {
        return new Queue(TopicRabbitConfig.LOG_USER_UPDATE);
    }

    /***
     * 用户日志交换机
     * @author lucky
     * @date 2020/8/18
     * @return
     **/
    @Bean
    TopicExchange userExchange() {
        return new TopicExchange("logExchange");
    }

    /***
     * 将userQueue和userExchange绑定,而且绑定的键值为log.user.add
     * 这样只要是消息携带的路由键是log.user.add,才会分发到该队列
     * @author lucky
     * @date 2020/8/18
     * @return
     **/
    @Bean
    Binding bindingUserExchangeMessage1() {
        return BindingBuilder.bind(userAddQueue()).to(userExchange()).with(LOG_USER_ADD);
    }

    /***
     * 将userUptQueue和userExchange绑定,而且绑定的键值为用上通配路由键规则log.user.#
     * 这样只要是消息携带的路由键是以log.user.开头,都会分发到该队列
     * @author lucky
     * @date 2020/8/18
     * @return
     **/
    @Bean
    Binding bindingUserExchangeMessage2(){
        return BindingBuilder.bind(userUptQueue()).to(userExchange()).with("log.user.#");
    }

    /**
     * 定义系统交换机，同时持久化
     *
     * @return EXCHANGE_TOPIC
     */
    @Bean(EXCHANGE_TOPIC)
    public Exchange exchangeSystem() {
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPIC).durable(true).build();
    }

    /**
     * 定义发布系统消息，同时持久化
     *
     * @return QUEUE_SYSTEM
     */
    @Bean(QUEUE_SYSTEM)
    public Queue queueSystem() {
        return new Queue(QUEUE_SYSTEM, true);
    }

    /**
     * 定义反馈系统消息，同时持久化
     *
     * @return QUEUE_SYSTEM
     */
    @Bean(QUEUE_SYSTEM_FEEDBACK)
    public Queue queueSystemFeedback() {
        return new Queue(QUEUE_SYSTEM, true);
    }

    /**
     * 绑定发布交换机和队列，同时路由到指定routingKey
     *
     * @return 绑定结果
     */
    @Bean
    public Binding bindingSystem() {
        return BindingBuilder.bind(queueSystem()).to(exchangeSystem()).with(ROUTING_KEY_SYSTEM).noargs();
    }

    /**
     * 绑定反馈交换机和队列，同时路由到指定routingKey
     *
     * @return 绑定结果
     */
    @Bean
    public Binding bindingSystemFeedback() {
        return BindingBuilder.bind(queueSystemFeedback()).to(exchangeSystem()).with(ROUTING_KEY_SYSTEM_FEEDBACK).noargs();
    }

}
