package com.futureai.write.domain.entity;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.futureai.write.types.dp.KeyValue;
import com.futureai.write.common.exception.ServiceException;
import lombok.*;

import java.util.List;

/**
 * @author max
 * @description 写作配置领域实体
 * @date 2024/3/6 15:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class WriteConfigEntity {
    private Long id;
    private String configName;
    private String configKey;
    private String configIcon;
    private Integer isDefault;
    private Integer sort;

    private List<KeyValue> materialList;
    private List<WriteConfigStructureEntity> configStructures;
    private List<WriteConfigInputEntity> configInputs;


    public WriteConfigEntity(String configName, String configKey, String configIcon, Long id, Integer isDefault, Integer sort, List<KeyValue> materialList) {
        this.configName = configName;
        this.configKey = configKey;
        this.configIcon = configIcon;
        this.id = id;
        this.isDefault = isDefault;
        this.sort = sort;
        this.materialList = materialList;
    }

    /**
     * 获取论文结构配置模型Id
     *
     * @param structureName 论文结构配置名称
     */
    public Long getModelIdByStructureName(String structureName) {
        return this.configStructures.stream()
                .filter(structure -> StrUtil.equals(structure.getStructureName(), structureName))
                .map(WriteConfigStructureEntity::getStructureModelId)
                .findFirst()
                .orElse(null);
    }

    /**
     * 获取论文结构配置Id
     *
     * @param structureName 论文结构配置名称
     * @return Long
     */
    public Long getConfigIdByStructureName(String structureName) {
        return this.configStructures.stream()
                .filter(structure -> StrUtil.equals(structure.getStructureName(), structureName))
                .map(WriteConfigStructureEntity::getStructureModelId)
                .findFirst()
                .orElse(null);
    }

    /**
     * 根据配置键获取论文机构配置信息
     *
     * @param structureName 配置键
     * @return
     *//*
    public WriteConfigStructure getWriteConfigStructureByKey(String structureName,){
        WriteConfigStructureEntity writeConfigStructure = this.getConfigStructures().stream()
                .filter(structure -> StrUtil.equals(structure.getStructureName(), structureName))
                .findFirst()
                .orElse(null);

        if (ObjectUtil.isNull(writeConfigStructure)) {
            throw new ServiceException("配置获取失败");
        }

        writeConfigStructure.formatStructurePrompt(params);
    }*/

    /**
     * 配置论文结构提示词
     *
     * @param structureName 论文结构名称
     * @param params        参数
     */
    public void setConfigStructurePrompt(String structureName, List<KeyValue> params) {
        WriteConfigStructureEntity writeConfigStructure = this.getConfigStructures().stream()
                .filter(structure -> StrUtil.equals(structure.getStructureName(), structureName))
                .findFirst()
                .orElse(null);

        if (ObjectUtil.isNull(writeConfigStructure)) {
            throw new ServiceException("配置获取失败");
        }

        writeConfigStructure.formatStructurePrompt(params);
    }
}
