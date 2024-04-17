package com.futureai.write.infrastructure.persistence.repository;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.futureai.write.domain.entity.WriteConfigEntity;
import com.futureai.write.domain.repository.IWriteConfigRepository;
import com.futureai.write.infrastructure.persistence.builder.WriteConfigBuilder;
import com.futureai.write.infrastructure.persistence.db.WriteConfigDO;
import com.futureai.write.infrastructure.persistence.db.WriteConfigInputDO;
import com.futureai.write.infrastructure.persistence.db.WriteConfigStructureDO;
import com.futureai.write.infrastructure.persistence.mapper.WriteConfigInputMapper;
import com.futureai.write.infrastructure.persistence.mapper.WriteConfigMapper;
import com.futureai.write.infrastructure.persistence.mapper.WriteConfigStructureMapper;
import com.futureai.write.types.dp.KeyValue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: matf
 * @create: 2024-03-08
 **/
@Component
@AllArgsConstructor
public class WriteConfigRepositoryImpl implements IWriteConfigRepository {
    private final WriteConfigBuilder writeConfigBuilder;

    private final WriteConfigMapper writeConfigMapper;
    private final WriteConfigInputMapper writeConfigInputMapper;
    private final WriteConfigStructureMapper writeConfigStructureMapper;

    @Override
    public List<WriteConfigEntity> findList() {
        List<WriteConfigDO> writeConfigDOList = writeConfigMapper.selectList(
                Wrappers.<WriteConfigDO>lambdaQuery()
                        .orderByDesc(WriteConfigDO::getSort)
        );
        return writeConfigBuilder.toEntityList(writeConfigDOList);
    }

    @Override
    public WriteConfigEntity find(Long configId) {
        WriteConfigDO writeConfigDO = writeConfigMapper.selectById(configId);

        return setConfigDetail(writeConfigDO);
    }

    @Override
    public WriteConfigEntity findByKey(String configKey) {
        WriteConfigDO writeConfigDO = writeConfigMapper.selectOne(
                Wrappers.<WriteConfigDO>lambdaQuery()
                        .eq(WriteConfigDO::getConfigKey, configKey)
                        .last("limit 1")
        );

        return setConfigDetail(writeConfigDO);
    }

    private WriteConfigEntity setConfigDetail(WriteConfigDO configDO) {
        List<WriteConfigInputDO> configInputDOList = new ArrayList<>();
        List<WriteConfigStructureDO> configStructureDOList = new ArrayList<>();
        List<KeyValue> materialList = new ArrayList<>();
        if (ObjectUtil.isNotNull(configDO)) {
            configInputDOList = writeConfigInputMapper.selectList(
                    Wrappers.<WriteConfigInputDO>lambdaQuery()
                            .eq(WriteConfigInputDO::getConfigId, configDO.getId())
            );
            configStructureDOList = writeConfigStructureMapper.selectList(
                    Wrappers.<WriteConfigStructureDO>lambdaQuery()
                            .eq(WriteConfigStructureDO::getConfigId, configDO.getId())
                            .orderByAsc(WriteConfigStructureDO::getSort)
            );
            materialList = JSONUtil.toList(configDO.getMaterialList(), KeyValue.class);
        }


        return writeConfigBuilder.toEntity(configDO, materialList, configInputDOList, configStructureDOList);
    }
}
