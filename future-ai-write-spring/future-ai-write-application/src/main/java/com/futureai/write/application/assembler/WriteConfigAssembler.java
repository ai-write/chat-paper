package com.futureai.write.application.assembler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.futureai.write.application.model.dto.SystemDictTreeDTO;
import com.futureai.write.application.model.dto.WriteConfigDetailDTO;
import com.futureai.write.application.model.dto.WriteConfigListDTO;
import com.futureai.write.domain.entity.SystemDictEntity;
import com.futureai.write.domain.entity.WriteConfigEntity;
import com.futureai.write.domain.entity.WriteConfigInputEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: matf
 * @create: 2024-03-08
 **/
@Component
@AllArgsConstructor
public class WriteConfigAssembler {

    private final SystemDictAssembler dictAssembler;

    public List<WriteConfigListDTO> toConfigList(List<WriteConfigEntity> writeConfigEntities) {
        return writeConfigEntities.stream()
                .map(config -> new WriteConfigListDTO(
                        config.getConfigName(),
                        config.getConfigKey(),
                        config.getConfigIcon(),
                        config.getIsDefault())
                ).collect(Collectors.toList());
    }

    public WriteConfigDetailDTO toConfigDetail(WriteConfigEntity entity, List<SystemDictEntity> dictList) {

        List<WriteConfigDetailDTO.Input> inputs = new ArrayList<>();
        List<WriteConfigInputEntity> inputList = entity.getConfigInputs();
        if (CollUtil.isNotEmpty(inputList)) {
            Map<String, List<SystemDictTreeDTO>> writeConfigMap = dictList.stream()
                    .collect(Collectors.groupingBy(SystemDictEntity::getCode))
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                        List<SystemDictEntity> groupDictList = entry.getValue();
                        return dictAssembler.toDictTree(groupDictList);
                    }));


            inputs = inputList.stream()
                    .map(input -> {
                        List<SystemDictTreeDTO> dictTreeList = new ArrayList<>();
                        if (StrUtil.isNotEmpty(input.getApiSign())) {
                            dictTreeList = writeConfigMap.get(input.getApiSign());
                        }
                        return new WriteConfigDetailDTO.Input(
                                input.getName(),
                                input.getDescription(),
                                input.getFormType(),
                                input.getApiSign(),
                                input.getFormKey(),
                                input.getTip(),
                                input.getSpan(),
                                input.getRule(),
                                dictTreeList
                        );
                    })
                    .collect(Collectors.toList());
        }

        return new WriteConfigDetailDTO(entity.getMaterialList(),inputs);


    }
}
