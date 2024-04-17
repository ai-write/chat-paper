package com.futureai.write.application.facade;

import com.futureai.write.domain.entity.WriteConfigEntity;
import com.futureai.write.domain.entity.WritePaperEntity;
import com.futureai.write.types.dp.AIChatRequest;
import com.futureai.write.types.dp.AIChatResponse;

/**
 * @author max
 * @description AI聊天防腐接口层
 * @date 2024/3/15 09:45
 */
public interface IOpenAIChatFacade {

    AIChatResponse generateOutline(AIChatRequest request);

    AIChatResponse generatePaper(String connectId, WritePaperEntity paperEntity, WriteConfigEntity writeConfigEntity, String apiKey);
}
