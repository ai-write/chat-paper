package com.futureai.write.domain.service;

import com.futureai.write.domain.entity.WriteConfigEntity;
import com.futureai.write.domain.entity.WritePaperEntity;
import com.futureai.write.types.dp.AIChatResponse;

/**
 * @author max
 * @description 写作论文领域服务接口
 * @date 2024/3/17 10:42
 */
public interface IWritePaperService {

    /**
     * 添加论文结构
     * @param paperEntity 论文数据
     * @param configEntity 论文写作配置
     * @param response AI返回结果
     */
    void addPaperStructure(WritePaperEntity paperEntity,WriteConfigEntity configEntity, AIChatResponse response);

    void formatPaperStructure(WritePaperEntity paperEntity, WriteConfigEntity configEntity);
}
