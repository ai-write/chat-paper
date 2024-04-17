package com.futureai.write.infrastructure.persistence.repository;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.futureai.write.domain.entity.SystemDictEntity;
import com.futureai.write.domain.repository.ISystemDictRepository;
import com.futureai.write.infrastructure.persistence.builder.SystemDictBuilder;
import com.futureai.write.infrastructure.persistence.db.SystemDictDO;
import com.futureai.write.infrastructure.persistence.mapper.SystemDictMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author max
 * @description 系统字典仓储实现
 * @date 2024/3/7 14:37
 */
@Repository
@AllArgsConstructor
public class SystemDictRepositoryImpl implements ISystemDictRepository {


    private final SystemDictBuilder systemDictBuilder;
    private final SystemDictMapper systemDictMapper;

    @Override
    public List<SystemDictEntity> findListByCode(String code) {

        List<SystemDictDO> doList = systemDictMapper.selectList(
                Wrappers.<SystemDictDO>lambdaQuery()
                        .eq(SystemDictDO::getCode, code)
                        .ne(SystemDictDO::getDictValue, -1)
        );
        return systemDictBuilder.toEntityList(doList);
    }

    @Override
    public List<SystemDictEntity> findListByCodeList(List<String> codeList) {
        if (CollUtil.isEmpty(codeList)){
            return new ArrayList<>();
        }
        List<SystemDictDO> doList = systemDictMapper.selectList(
                Wrappers.<SystemDictDO>lambdaQuery()
                        .in(SystemDictDO::getCode, codeList)
                        .ne(SystemDictDO::getDictValue, -1)
        );

        return systemDictBuilder.toEntityList(doList);
    }
}
