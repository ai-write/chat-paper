package com.futureai.write.types.dp;

import cn.hutool.core.util.StrUtil;
import com.futureai.write.common.exception.InvalidParameterException;
import lombok.Data;

/**
 * @author max
 * @description AI请求DP
 * @date 2024/3/14 16:50
 */
@Data
public class AIChatRequest {
    public AIChatRequest(String modelName, String proxyUrl, String apiKey, String requestMessage) {
        if (StrUtil.isEmpty(modelName)){
            throw new InvalidParameterException("modelName 不得为空");
        }
        if (StrUtil.isEmpty(proxyUrl)){
            throw new InvalidParameterException("proxyUrl 不得为空");
        }
        if (StrUtil.isEmpty(apiKey)){
            throw new InvalidParameterException("apiKey 不得为空");
        }
        if (StrUtil.isEmpty(requestMessage)){
            throw new InvalidParameterException("requestMessage 不得为空");
        }

        this.modelName = modelName;
        this.proxyUrl = proxyUrl;
        this.apiKey = apiKey;
        this.requestMessage = requestMessage;
    }

    private String modelName;

    private String proxyUrl;

    private String apiKey;

    private String requestMessage;
}
