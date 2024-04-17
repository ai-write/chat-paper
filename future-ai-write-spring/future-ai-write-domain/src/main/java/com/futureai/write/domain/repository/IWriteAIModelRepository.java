package com.futureai.write.domain.repository;

import com.futureai.write.domain.entity.WriteAIModelEntity;

/**
 * @author max
 * @description 写作AI模型领域仓储接口
 * @date 2024/3/6 15:09
 */
public interface IWriteAIModelRepository {

    /**
     * 根据modelId查找
     * @param modelId 模型Id
     * @return
     */
    WriteAIModelEntity find(Long modelId);
}
