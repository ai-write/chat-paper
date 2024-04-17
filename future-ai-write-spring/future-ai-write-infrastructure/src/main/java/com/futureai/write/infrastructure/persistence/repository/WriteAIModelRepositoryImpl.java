package com.futureai.write.infrastructure.persistence.repository;

import com.futureai.write.domain.entity.WriteAIModelEntity;
import com.futureai.write.domain.repository.IWriteAIModelRepository;
import com.futureai.write.infrastructure.persistence.builder.WriteAIModelBuilder;
import com.futureai.write.infrastructure.persistence.db.WriteAIModelDO;
import com.futureai.write.infrastructure.persistence.mapper.WriteAIModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author max
 * @description 写作模型仓储实现
 * @date 2024/3/14 01:30
 */
@Component
@AllArgsConstructor
public class WriteAIModelRepositoryImpl implements IWriteAIModelRepository {

    private final WriteAIModelBuilder writeAIModelBuilder;
    private final WriteAIModelMapper writeAIModelMapper;

    @Override
    public WriteAIModelEntity find(Long modelId) {
        WriteAIModelDO modelDO = writeAIModelMapper.selectById(modelId);
        return writeAIModelBuilder.toEntity(modelDO);
    }
}
