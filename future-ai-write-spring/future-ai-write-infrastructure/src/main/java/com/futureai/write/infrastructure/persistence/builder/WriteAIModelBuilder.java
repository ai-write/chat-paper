package com.futureai.write.infrastructure.persistence.builder;

import com.futureai.write.domain.entity.WriteAIModelEntity;
import com.futureai.write.infrastructure.persistence.db.WriteAIModelDO;
import org.springframework.stereotype.Component;

/**
 * @author max
 * @description AI写作模型Builder
 * @date 2024/3/14 01:34
 */
@Component
public class WriteAIModelBuilder {

    public WriteAIModelEntity toEntity(WriteAIModelDO aiModelDO){
        return new WriteAIModelEntity(
                aiModelDO.getId(),
                aiModelDO.getModelName(),
                aiModelDO.getProxyUrl(),
                aiModelDO.getApiKey()
        );
    }
}
