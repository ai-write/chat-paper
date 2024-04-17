package com.futureai.write.infrastructure.persistence.repository;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.futureai.write.domain.entity.WritePaperEntity;
import com.futureai.write.domain.repository.IWritePaperRepository;
import com.futureai.write.infrastructure.persistence.builder.WritePaperBuilder;
import com.futureai.write.infrastructure.persistence.db.WritePaperDO;
import com.futureai.write.infrastructure.persistence.db.WritePaperStructureDO;
import com.futureai.write.infrastructure.persistence.mapper.WritePaperMapper;
import com.futureai.write.infrastructure.persistence.mapper.WritePaperStructureMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author max
 * @description 写作论文仓储实现
 * @date 2024/3/17 11:30
 */
@Repository
@AllArgsConstructor
public class WritePaperRepository implements IWritePaperRepository {
    private final WritePaperBuilder writePaperBuilder;

    private final WritePaperMapper writePaperMapper;
    private final WritePaperStructureMapper writePaperStructureMapper;

    @Override
    public WritePaperEntity find(Long writePaperId) {
        WritePaperDO paperDO = writePaperMapper.selectById(writePaperId);
        List<WritePaperStructureDO> structureDOList = writePaperStructureMapper.selectList(
                Wrappers.<WritePaperStructureDO>lambdaQuery()
                        .eq(WritePaperStructureDO::getPaperId, writePaperId)
        );
        if (CollUtil.isEmpty(structureDOList)) {
            structureDOList = new ArrayList<>();
        }
        return writePaperBuilder.toEntity(paperDO, structureDOList);
    }

    @Override
    public void save(WritePaperEntity paperEntity) {
        WritePaperDO paperDO = writePaperBuilder.toDO(paperEntity);
        paperDO.setCreateTime(new Date());
        writePaperMapper.insert(paperDO);

        paperEntity.setId(paperDO.getId());

        if (CollUtil.isNotEmpty(paperEntity.getStructures())) {
            List<WritePaperStructureDO> paperStructureList = writePaperBuilder.toStructureDOList(paperEntity);
            paperStructureList = paperStructureList.stream().peek(structure -> {
                structure.setCreateTime(new Date());
                structure.setPaperId(paperEntity.getId());
            }).collect(Collectors.toList());
            paperStructureList.forEach(writePaperStructureMapper::insert);

            paperEntity.getStructures()
                    .forEach(structure -> structure.setId(structure.getId()));
        }
    }

    @Override
    public void modify(WritePaperEntity paperEntity) {
        WritePaperDO paperDO = writePaperBuilder.toDO(paperEntity);
        writePaperMapper.updateById(paperDO);

        List<WritePaperStructureDO> paperStructureList = writePaperBuilder.toStructureDOList(paperEntity);
        List<WritePaperStructureDO> savedPaperStructureList = writePaperStructureMapper.selectList(
                Wrappers.<WritePaperStructureDO>lambdaQuery()
                        .eq(WritePaperStructureDO::getPaperId, paperEntity.getId())
        );
        paperStructureList.forEach(structure -> {
            boolean noneMatch = savedPaperStructureList.stream()
                    .noneMatch(savedData -> savedData.getId().equals(structure.getId()));
            if (noneMatch) {
                structure.setPaperId(paperEntity.getId());
                writePaperStructureMapper.insert(structure);
            }
        });
    }
}
