package com.kcm.config.rabbitmq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.MessageConversionException;

import java.io.UnsupportedEncodingException;

/**
 * 自定义消息转换器
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/8/26 10:59
 */
@Slf4j
public class RabbitmqFastJsonConverter extends AbstractMessageConverter {

    private static final ClassMapper CLASS_MAPPER = new RabbitmqFastJsonClassMapper();

    private static final String DEFAULT_CHAR_SET = "UTF-8";

    private static final String CONTENT_TYPE_SUFFIX = "json";

    /**
     * 创建消息
     *
     * @param o Object
     * @param messageProperties MessageProperties
     * @return Message
     */
    @Override
    protected Message createMessage(Object o, MessageProperties messageProperties) {
        byte[] bytes;
        try {
            String jsonString = JSON.toJSONString(o);
            bytes = jsonString.getBytes(DEFAULT_CHAR_SET);
        } catch (UnsupportedEncodingException e) {
            throw new MessageConversionException("Failed to convert Message content", e);
        }
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        messageProperties.setContentEncoding(DEFAULT_CHAR_SET);
        messageProperties.setContentLength(bytes.length);
        CLASS_MAPPER.fromClass(o.getClass(), messageProperties);
        return new Message(bytes, messageProperties);
    }

    /**
     * 消息转换
     *
     * @param message message
     * @return 转换结果
     * @throws MessageConversionException 消息转换异常
     */
    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        Object content = null;
        MessageProperties properties = message.getMessageProperties();
        if (properties != null) {
            String contentType = properties.getContentType();
            if (contentType != null && contentType.contains(CONTENT_TYPE_SUFFIX)) {
                String encoding = properties.getContentEncoding();
                if (encoding != null) {
                    encoding = DEFAULT_CHAR_SET;
                }
                try {
                    Class<?> targetClass = CLASS_MAPPER.toClass(message.getMessageProperties());
                    content = deserialize(message.getBody(), encoding, targetClass);
                } catch (UnsupportedEncodingException e) {
                    throw new MessageConversionException("Failed to convert Message content", e);
                }
            } else {
                log.error("Could not convert incoming message with content-type [" + contentType + "]");
            }
        }
        if (content == null) {
            content = message.getBody();
        }
        return content;
    }

    private Object deserialize(byte[] bytes, String encoding, Class<?> clazz) throws UnsupportedEncodingException {
        String content = new String(bytes, encoding);
        return JSON.parseObject(content, clazz);
    }

}
