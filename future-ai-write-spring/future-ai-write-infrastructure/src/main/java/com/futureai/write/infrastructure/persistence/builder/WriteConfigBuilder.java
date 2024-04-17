package com.futureai.write.infrastructure.persistence.builder;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.futureai.write.domain.entity.WriteConfigEntity;
import com.futureai.write.domain.entity.WriteConfigInputEntity;
import com.futureai.write.domain.entity.WriteConfigStructureEntity;
import com.futureai.write.infrastructure.persistence.db.WriteConfigDO;
import com.futureai.write.infrastructure.persistence.db.WriteConfigInputDO;
import com.futureai.write.infrastructure.persistence.db.WriteConfigStructureDO;
import com.futureai.write.types.dp.KeyValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: matf
 * @create: 2024-03-08
 **/
@Component
public class WriteConfigBuilder {

    public WriteConfigEntity toEntity(WriteConfigDO config, List<KeyValue> materialList, List<WriteConfigInputDO> configInputList, List<WriteConfigStructureDO> configStructureList) {

        List<WriteConfigStructureEntity> configStructures = configStructureList.stream()
                .map(structure -> new WriteConfigStructureEntity(
                        structure.getId(),
                        structure.getStructureName(),
                        structure.getStructureModelId(),
                        structure.getStructurePrompt()
                ))
                .collect(Collectors.toList());

        List<WriteConfigInputEntity> configInputEntities = configInputList.stream()
                .map(input -> new WriteConfigInputEntity(
                        input.getId(),
                        input.getConfigId(),
                        input.getName(),
                        input.getDescription(),
                        input.getFormType(),
                        input.getApiSign(),
                        input.getFormKey(),
                        input.getTip(),
                        input.getSpan(),
                        input.getRule())
                ).collect(Collectors.toList());

        return new WriteConfigEntity(
                config.getId(),
                config.getConfigName(),
                config.getConfigKey(),
                config.getConfigIcon(),
                config.getIsDefault(),
                config.getSort(),
                materialList,
                configStructures,
                configInputEntities
        );

    }


    public List<WriteConfigEntity> toEntityList(List<WriteConfigDO> doList) {
        if (CollUtil.isEmpty(doList)) {
            return new ArrayList<>();
        }
        return doList.stream()
                .map(config -> {
                            List<KeyValue> materialList = new ArrayList<>();
                            if (StrUtil.isNotEmpty(config.getMaterialList())) {
                                materialList = JSONUtil.toList(config.getMaterialList(), KeyValue.class);
                            }
                            return new WriteConfigEntity(
                                    config.getConfigName(),
                                    config.getConfigKey(),
                                    config.getConfigIcon(),
                                    config.getId(),
                                    config.getIsDefault(),
                                    config.getSort(),
                                    materialList
                            );
                        }
                ).collect(Collectors.toList());
    }
}
