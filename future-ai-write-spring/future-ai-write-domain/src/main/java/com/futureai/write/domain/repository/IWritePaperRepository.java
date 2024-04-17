package com.futureai.write.domain.repository;

import com.futureai.write.domain.entity.WritePaperEntity;

/**
 * @author max
 * @description 写作论文仓储接口
 * @date 2024/3/17 11:28
 */
public interface IWritePaperRepository {

    /**
     * 查找
     * @param writePaperId 论文写作Id
     * @return
     */
    WritePaperEntity find(Long writePaperId);

    /**
     * 保存
     *
     * @param paperEntity 写作论文实体
     */
    void save(WritePaperEntity paperEntity);

    /**
     * 编辑
     *
     * @param paperEntity 写作论文实体
     */
    void modify(WritePaperEntity paperEntity);
}
