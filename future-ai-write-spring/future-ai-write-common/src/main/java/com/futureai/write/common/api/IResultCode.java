package com.futureai.write.common.api;

import java.io.Serializable;

/**
 * @author max
 * @description
 * @date 2024/2/19 14:28
 */
public interface IResultCode extends Serializable {

    /**
     * 获取消息
     *
     * @return
     */
    String getMessage();

    /**
     * 获取状态码
     *
     * @return
     */
    int getCode();

}
