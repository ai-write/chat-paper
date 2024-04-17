package com.futureai.write.infrastructure.external.service;

import com.futureai.write.types.dp.AIChatRequest;
import com.futureai.write.types.dp.AIChatResponse;

/**
 * @author max
 * @description AI对话服务请求接口
 * @date 2024/3/14 18:12
 */
public interface IOpenAIChatService {

    AIChatResponse chatNormal(AIChatRequest request);

    AIChatResponse chatStream(String connectId, AIChatRequest request);
}
