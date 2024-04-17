package com.futureai.write.domain.service;

import com.futureai.write.domain.entity.WriteAIModelEntity;
import com.futureai.write.domain.entity.WriteConfigEntity;
import com.futureai.write.domain.enums.WriteStructureEnum;
import com.futureai.write.types.dp.AIChatRequest;

/**
 * @author max
 * @description 写作配置领域服务接口
 * @date 2024/3/14 16:54
 */
public interface IWriteConfigService {

    /**
     * 根据AI写作配置获取AI模型请求
     *
     * @param configEntity  写作配置领域实体
     * @param structureEnum 写作结构枚举
     * @param apiKey        Api请求密钥
     * @return
     */
    AIChatRequest getAIRequestByConfig(WriteConfigEntity configEntity, WriteStructureEnum structureEnum, String apiKey);
}
