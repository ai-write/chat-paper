package com.futureai.write.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.futureai.write.domain.entity.WriteAIModelEntity;
import com.futureai.write.domain.entity.WriteConfigEntity;
import com.futureai.write.domain.entity.WriteConfigStructureEntity;
import com.futureai.write.domain.enums.WriteStructureEnum;
import com.futureai.write.domain.repository.IWriteAIModelRepository;
import com.futureai.write.domain.service.IWriteConfigService;
import com.futureai.write.types.constant.WriteMessageConstant;
import com.futureai.write.types.dp.AIChatRequest;
import com.futureai.write.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author max
 * @description 写作配置领域服务实现
 * @date 2024/3/14 17:08
 */
@Service
@AllArgsConstructor
public class WriteConfigServiceImpl implements IWriteConfigService {

    private final IWriteAIModelRepository modelRepository;

    @Override
    public AIChatRequest getAIRequestByConfig(WriteConfigEntity configEntity, WriteStructureEnum structureEnum, String apiKey) {

        Long structureModelId = configEntity.getModelIdByStructureName(structureEnum.getName());
        WriteAIModelEntity modelEntity = modelRepository.find(structureModelId);
        if (ObjectUtil.isNull(modelEntity)){
            throw new ServiceException(WriteMessageConstant.AI_MODEL_NOT_FOUND);
        }

        if (StrUtil.isNotEmpty(apiKey)){
            modelEntity.setApiKey(apiKey);
        }

        WriteConfigStructureEntity writeConfigStructure = configEntity.getConfigStructures().stream()
                .filter(structure -> structure.getStructureModelId().equals(modelEntity.getId()))
                .findFirst()
                .orElse(null);

        if (ObjectUtil.isNull(writeConfigStructure)){
            throw new ServiceException(WriteMessageConstant.WRITE_CONFIG_NOT_FOUND);
        }

        return new AIChatRequest(modelEntity.getModelName(), modelEntity.getProxyUrl(), modelEntity.getApiKey(), writeConfigStructure.getStructurePrompt());
    }
}
