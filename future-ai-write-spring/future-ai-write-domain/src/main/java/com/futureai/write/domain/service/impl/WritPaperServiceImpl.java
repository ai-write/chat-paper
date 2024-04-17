package com.futureai.write.domain.service.impl;

import cn.hutool.core.util.StrUtil;
import com.futureai.write.common.exception.ServiceException;
import com.futureai.write.domain.entity.WriteConfigEntity;
import com.futureai.write.domain.entity.WritePaperEntity;
import com.futureai.write.domain.service.IWritePaperService;
import com.futureai.write.types.constant.WriteMessageConstant;
import com.futureai.write.types.dp.AIChatResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author max
 * @description 写作论文领域服务实现
 * @date 2024/3/17 10:46
 */
@Slf4j
@Service
@AllArgsConstructor
public class WritPaperServiceImpl implements IWritePaperService {
    @Override
    public void addPaperStructure(WritePaperEntity paperEntity, WriteConfigEntity configEntity, AIChatResponse response) {

    }

    @Override
    public void formatPaperStructure(WritePaperEntity paperEntity, WriteConfigEntity configEntity) {


    }
}
