package com.futureai.write.common.handler.pool;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Set;

/**
 * @author max
 * @description SSE缓存池
 * @date 2024/3/19 10:38
 */
public class CacheSSEPool {


    /**
     * 默认缓存过期时长 毫秒 2分钟
     */
    public final static long DEFAULT_TIMEOUT = 1000 * 60 * 10;

    private static final TimedCache<String, SseEmitter> CACHE = CacheUtil.newTimedCache(DEFAULT_TIMEOUT);

    static {
        // 定时清理
        CACHE.schedulePrune(1000 * 20);
    }

    /**
     * 获取缓存
     *
     * @param sseId
     * @return
     */
    public static SseEmitter get(String sseId) {
        return CACHE.get(sseId);
    }

    /**
     * 添加缓存
     *
     * @param sseId
     * @param sse
     */
    public static void add(String sseId, SseEmitter sse) {
        CACHE.put(sseId, sse);
    }

    /**
     * 从缓存池中移除
     *
     * @param sseId
     */
    public static void remove(String sseId) {
        SseEmitter sse = get(sseId);
        if (ObjectUtil.isNull(sse)) {
            return;
        }
        ResponseBodyEmitter responseBodyEmitter = new ResponseBodyEmitter();
        responseBodyEmitter.complete();
        CACHE.remove(sseId);
    }

    /**
     * 刷新缓存
     *
     * @param sseId
     */
    public static void flushCache(String sseId) {
        SseEmitter sse = get(sseId);
        if (ObjectUtil.isNull(sse)) {
            return;
        }
        CACHE.put(sseId, sse, DEFAULT_TIMEOUT);
    }

    /**
     * 获取缓存池的key集合
     *
     * @return
     */
    public static Set<String> poolSet() {
        return CACHE.keySet();
    }

    /**
     * 获取缓存池key数量
     *
     * @return
     */
    public static int poolCacheSize() {
        return poolSet().size();
    }

}
