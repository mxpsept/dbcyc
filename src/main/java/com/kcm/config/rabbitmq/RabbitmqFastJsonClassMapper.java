package com.kcm.config.rabbitmq;

import org.springframework.amqp.support.converter.DefaultClassMapper;

/**
 * mq映射转换
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/8/26 10:58
 */
public class RabbitmqFastJsonClassMapper extends DefaultClassMapper {

    public RabbitmqFastJsonClassMapper() {
        super();
        //设置信任所有package
        setTrustedPackages("*");
    }

}
