package com.futureai.write.common.handler.pool;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.futureai.write.common.exception.InvalidParameterException;
import com.futureai.write.common.handler.MessageSendHandler;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author max
 * @description 消息发送器连接池
 * @date 2024/3/19 10:17
 */
public class MessageSendPool {

    public static final ConcurrentHashMap<String, MessageSendHandler> CACHE = new ConcurrentHashMap<>();

    /**
     * 获取消息处理器
     * @param key key
     * @return
     */
    public static MessageSendHandler get(String key) {
        return CACHE.get(key);
    }

    /**
     * 添加链接
     * @param key key
     * @param messageSendHandler messageSendHandler
     */
    public static void add(String key,MessageSendHandler messageSendHandler){
        if (StrUtil.isEmpty(key)){
            throw new InvalidParameterException("Key不得为空");
        }
        if (ObjectUtil.isNull(messageSendHandler)){
            throw new InvalidParameterException("MessageSendHandler不得为空");
        }
        CACHE.put(key,messageSendHandler);
    }

    public static void remove(String key) {
        if (StrUtil.isEmpty(key)){
            throw new InvalidParameterException("Key不得为空");
        }
        MessageSendHandler messageSendHandler = get(key);
        if (ObjectUtil.isNull(messageSendHandler)){
            throw new InvalidParameterException("MessageSendHandler不得为空");
        }

        messageSendHandler.stop();
        CACHE.remove(key);
    }

}
