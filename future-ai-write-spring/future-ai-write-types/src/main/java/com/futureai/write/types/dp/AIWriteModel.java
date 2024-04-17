package com.futureai.write.types.dp;

import lombok.Data;

/**
 * @author max
 * @description AI写作模型
 * @date 2024/3/17 11:03
 */
@Data
public class AIWriteModel {

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 模型名称
     */
    private String proxyUrl;

    /**
     * Api请求Key
     */
    private String apiKey;
}
