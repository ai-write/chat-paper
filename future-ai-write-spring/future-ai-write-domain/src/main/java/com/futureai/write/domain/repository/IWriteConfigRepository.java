package com.futureai.write.domain.repository;

import com.futureai.write.domain.entity.WriteConfigEntity;

import java.util.List;

public interface IWriteConfigRepository {

    /**
     * 获取配置列表
     * @return
     */
    List<WriteConfigEntity> findList();

    /**
     * 获取配置信息
     * @param configId 配置Id
     * @return
     */
    WriteConfigEntity find(Long configId);

    /**
     * 根据配置键获取配置信息
     * @param configKey 配置键
     * @return
     */
    WriteConfigEntity findByKey(String configKey);
}
