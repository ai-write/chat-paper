package com.futureai.write.infrastructure.persistence.builder;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.futureai.write.domain.entity.WritePaperEntity;
import com.futureai.write.domain.entity.WritePaperStructureEntity;
import com.futureai.write.infrastructure.persistence.db.WritePaperDO;
import com.futureai.write.infrastructure.persistence.db.WritePaperStructureDO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author max
 * @description 写作论文建造类
 * @date 2024/3/17 11:34
 */
@Component
public class WritePaperBuilder {

    public WritePaperDO toDO(WritePaperEntity entity) {
        WritePaperDO paperDO = BeanUtil.copyProperties(entity, WritePaperDO.class);
        paperDO.setUpdateTime(new Date());

        return paperDO;
    }

    public List<WritePaperStructureDO> toStructureDOList(WritePaperEntity entity) {
        if (CollUtil.isEmpty(entity.getStructures())) {
            return new ArrayList<>();
        }

        List<WritePaperStructureDO> structureDOList = BeanUtil.copyToList(entity.getStructures(), WritePaperStructureDO.class);
        return structureDOList.stream()
                .peek(structure -> {
                    structure.setUpdateTime(new Date());
                }).collect(Collectors.toList());
    }

    public WritePaperEntity toEntity(WritePaperDO paperDO, List<WritePaperStructureDO> structureDOList) {
        return new WritePaperEntity(
                paperDO.getId(),
                paperDO.getConfigId(),
                paperDO.getTitle(),
                paperDO.getDetail(),
                paperDO.getGenerateTime(),
                paperDO.getContent(),
                paperDO.getOssUrl(),
                paperDO.getGenerateStatus(),
                paperDO.getSubmitTime(),
                paperDO.getCompleteTime(),
                structureDOList.stream()
                        .map(structure -> new WritePaperStructureEntity(
                                structure.getId(),
                                structure.getStructureId(),
                                structure.getData(),
                                structure.getExecuteTime(),
                                structure.getStartTime(),
                                structure.getFinishTime()
                        )).collect(Collectors.toList())
        );
    }
}
