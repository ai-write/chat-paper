package com.futureai.write.application.facade.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.futureai.write.application.facade.IOpenAIChatFacade;
import com.futureai.write.common.exception.ServiceException;
import com.futureai.write.domain.entity.WriteConfigEntity;
import com.futureai.write.domain.entity.WritePaperEntity;
import com.futureai.write.domain.enums.WriteStructureEnum;
import com.futureai.write.domain.repository.IWriteConfigRepository;
import com.futureai.write.domain.service.IWriteConfigService;
import com.futureai.write.infrastructure.external.service.IOpenAIChatService;
import com.futureai.write.types.constant.WriteMessageConstant;
import com.futureai.write.types.dp.AIChatRequest;
import com.futureai.write.types.dp.AIChatResponse;
import com.futureai.write.types.dp.KeyValue;
import com.futureai.write.types.dp.PaperOutlineTree;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.K;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author max
 * @description AI聊天防腐实现
 * @date 2024/3/15 09:46
 */
@Service
@AllArgsConstructor
public class OpenAIChatFacadeImpl implements IOpenAIChatFacade {

    private final IOpenAIChatService openAIChatService;

    private final IWriteConfigService writeConfigService;
    private final IWriteConfigRepository writeConfigRepository;

    @Override
    public AIChatResponse generateOutline(AIChatRequest request) {
        return openAIChatService.chatNormal(request);
    }

    @Override
    public AIChatResponse generatePaper(String connectId, WritePaperEntity paperEntity, WriteConfigEntity writeConfigEntity, String apiKey) {


        Long outlineStructureId = writeConfigEntity.getConfigIdByStructureName(WriteStructureEnum.OUTLINE.getName());
        if (ObjectUtil.isNull(outlineStructureId)) {
            throw new ServiceException(WriteMessageConstant.WRITE_CONFIG_NOT_FOUND);
        }

        paperEntity.formatOutlineFromStructure(outlineStructureId);

        List<KeyValue> params = new ArrayList<>();
        params.add(new KeyValue("title", paperEntity.getTitle()));
        params.add(new KeyValue("outlines", JSONUtil.toJsonStr(paperEntity.getOutlines())));

        writeConfigEntity.setConfigStructurePrompt(WriteStructureEnum.PAPER.getName(), params);
        AIChatRequest chatRequest = writeConfigService.getAIRequestByConfig(writeConfigEntity, WriteStructureEnum.PAPER, apiKey);

        return openAIChatService.chatStream(connectId, chatRequest);
    }
}
