package com.futureai.write.common.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.futureai.write.common.api.ResultCode;
import com.futureai.write.common.exception.ServiceException;
import com.futureai.write.common.handler.pool.CacheSSEPool;
import com.futureai.write.common.handler.pool.MessageSendPool;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author max
 * @description 消息发送处理器
 * @date 2024/3/19 10:16
 */
public class MessageSendHandler {

    /**
     * 连接Id
     */
    private final String connectId;

    /**
     * 正在运行状态
     */
    private AtomicBoolean runningState = new AtomicBoolean(true);

    /**
     * 消息队列
     */
    private Queue<String> messageQueue = new ConcurrentLinkedQueue<>();

    /**
     * SSE发送器
     */
    private SseEmitter sseEmitter;

    /**
     * 停止状态
     */
    private AtomicBoolean stopState = new AtomicBoolean(false);

    /**
     * 构造函数
     *
     * @param connectId 连接Id
     */
    public MessageSendHandler(String connectId) {
        this.connectId = connectId;
        if (StrUtil.isEmpty(connectId)) {
            return;
        }
        this.sseEmitter = CacheSSEPool.get(this.connectId);
        MessageSendPool.add(connectId, this);
    }

    /**
     * 向队列中添加内容
     *
     * @param content 需要添加的内容
     */
    public void queueAdd(String content) {
        if (StrUtil.isEmpty(connectId)) {
            return;
        }

        if (stopState.get()) {
            throw new ServiceException(ResultCode.SUCCESS);
        }
        this.messageQueue.add(content);
        String poll = this.messageQueue.poll();
        if (ObjectUtil.isNotNull(this.sseEmitter) && StrUtil.isNotEmpty(poll)) {
            try {
                Thread.sleep(20);
                this.sseEmitter.send(SseEmitter.event().data(poll));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭
     */
    public void close() {
        if (StrUtil.isEmpty(this.connectId)) {
            return;
        }

        if (ObjectUtil.isNotNull(this.sseEmitter)) {
            this.sseEmitter.complete();
            CacheSSEPool.remove(this.connectId);
        }

        try {
            Thread.sleep(110);
            this.runningState.set(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 释放资源
        MessageSendPool.remove(this.connectId);
    }

    /**
     * 停止输出
     */
    public void stop() {
        if (StrUtil.isEmpty(connectId)) {
            this.stopState.set(true);
        }
    }
}
