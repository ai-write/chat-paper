package com.futureai.write.domain.repository;

import com.futureai.write.domain.entity.SystemDictEntity;

import java.util.List;

/**
 * @author max
 * @description 系统字典仓储接口
 * @date 2024/3/7 14:33
 */
public interface ISystemDictRepository  {

    /**
     * 根据字典码查询字典类标
     * @param code 字典码
     * @return
     */
    List<SystemDictEntity> findListByCode(String code);

    List<SystemDictEntity> findListByCodeList(List<String> codeList);
}
