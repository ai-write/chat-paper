package com.futureai.write.application.assembler;

import cn.hutool.core.collection.CollUtil;
import com.futureai.write.application.model.dto.SystemDictListDTO;
import com.futureai.write.application.model.dto.SystemDictTreeDTO;
import com.futureai.write.common.node.ForestNodeMerger;
import com.futureai.write.domain.entity.SystemDictEntity;
import com.futureai.write.types.dp.Dict;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author max
 * @description 系统字典转换
 * @date 2024/3/7 14:13
 */
@Component
public class SystemDictAssembler {

    public List<SystemDictListDTO> toDictList(List<SystemDictEntity> entityList) {
        if (CollUtil.isEmpty(entityList)) {
            return new ArrayList<>();
        }
        return entityList.stream()
                .map(entity -> new SystemDictListDTO(new Dict(entity.getDictKey(), entity.getDictValue())))
                .collect(Collectors.toList());
    }

    public List<SystemDictTreeDTO> toDictTree(List<SystemDictEntity> entityList) {
        if (CollUtil.isEmpty(entityList)) {
            return new ArrayList<>();
        }

        List<SystemDictTreeDTO> treeDTOList = entityList.stream()
                .map(entity -> {
                    SystemDictTreeDTO dto = new SystemDictTreeDTO();
                    dto.setId(entity.getId());
                    dto.setParentId(entity.getParentId());
                    dto.setLabel(entity.getDictKey());
                    dto.setValue(entity.getDictValue());
                    return dto;
                }).collect(Collectors.toList());

        return ForestNodeMerger.merge(treeDTOList);
    }
}
