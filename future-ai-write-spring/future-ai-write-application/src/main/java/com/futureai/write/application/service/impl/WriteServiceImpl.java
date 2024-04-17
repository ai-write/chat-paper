package com.futureai.write.application.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.futureai.write.application.assembler.WriteConfigAssembler;
import com.futureai.write.application.assembler.WritePaperAssembler;
import com.futureai.write.application.facade.IOpenAIChatFacade;
import com.futureai.write.application.model.command.GenerateOutlineCommand;
import com.futureai.write.application.model.command.GeneratePaperCommand;
import com.futureai.write.application.model.dto.GenerateOutlineDTO;
import com.futureai.write.application.model.dto.WriteConfigDetailDTO;
import com.futureai.write.application.model.dto.WriteConfigListDTO;
import com.futureai.write.application.service.IWriteService;
import com.futureai.write.domain.entity.SystemDictEntity;
import com.futureai.write.domain.entity.WriteConfigEntity;
import com.futureai.write.domain.entity.WriteConfigInputEntity;
import com.futureai.write.domain.entity.WritePaperEntity;
import com.futureai.write.domain.enums.WritePaperGenerateStatusEnum;
import com.futureai.write.domain.enums.WriteStructureEnum;
import com.futureai.write.domain.repository.ISystemDictRepository;
import com.futureai.write.domain.repository.IWriteConfigRepository;
import com.futureai.write.domain.repository.IWritePaperRepository;
import com.futureai.write.domain.service.IWriteConfigService;
import com.futureai.write.types.constant.WriteMessageConstant;
import com.futureai.write.types.dp.AIChatRequest;
import com.futureai.write.types.dp.AIChatResponse;
import com.futureai.write.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: matf
 * @create: 2024-03-08
 **/
@Slf4j
@Service
@AllArgsConstructor
public class WriteServiceImpl implements IWriteService {
    private final IWriteConfigService writeConfigService;

    private final IWriteConfigRepository writeConfigRepository;
    private final IWritePaperRepository writePaperRepository;
    private final ISystemDictRepository dictRepository;

    private final IOpenAIChatFacade openAIChatFacade;

    private final WriteConfigAssembler writeConfigAssembler;
    private final WritePaperAssembler writePaperAssembler;

    @Override
    public List<WriteConfigListDTO> getWriteConfig() {
        List<WriteConfigEntity> list = writeConfigRepository.findList();
        return writeConfigAssembler.toConfigList(list);
    }

    @Override
    public WriteConfigDetailDTO getWriteConfigInput(String configKey) {

        WriteConfigEntity configEntity = writeConfigRepository.findByKey(configKey);
        if (ObjectUtil.isNull(configEntity)) {
            throw new ServiceException("配置获取失败");
        }

        List<String> apiSignList = configEntity.getConfigInputs().stream()
                .map(WriteConfigInputEntity::getApiSign)
                .filter(ObjectUtil::isNotEmpty)
                .collect(Collectors.toList());

        List<SystemDictEntity> dictList = dictRepository.findListByCodeList(apiSignList);
        return writeConfigAssembler.toConfigDetail(configEntity, dictList);
    }

    @Override
    public GenerateOutlineDTO generateOutline(GenerateOutlineCommand command) {

        WriteConfigEntity configEntity = writeConfigRepository.findByKey(command.getConfigKey());
        if (ObjectUtil.isNull(configEntity)) {
            throw new ServiceException(WriteMessageConstant.GENERATE_OUTLINE_FAILURE);
        }

        configEntity.setConfigStructurePrompt(WriteStructureEnum.OUTLINE.getName(), command.getParams());
        AIChatRequest chatRequest = writeConfigService.getAIRequestByConfig(configEntity, WriteStructureEnum.OUTLINE, command.getApiKey());
        AIChatResponse response = openAIChatFacade.generateOutline(chatRequest);
        if (ObjectUtil.isNull(response) || StrUtil.isEmpty(response.getResponseContent())) {
            throw new ServiceException(WriteMessageConstant.GENERATE_OUTLINE_FAILURE);
        }

        // 保存论文记录
        Long writeConfigStructureId = configEntity.getConfigIdByStructureName(WriteStructureEnum.OUTLINE.getName());
        WritePaperEntity writePaperEntity = new WritePaperEntity(
                configEntity.getId(),
                JSONUtil.toJsonStr(command.getParams()),
                WritePaperGenerateStatusEnum.SUBMITTED,
                new Date()
        );

        // 添加大纲数据
        writePaperEntity.addPaperStructure(writeConfigStructureId, response);
        writePaperEntity.formatOutlineFromJson(response.getResponseContent());
        writePaperRepository.save(writePaperEntity);


        return writePaperAssembler.toGenerateOutlineDTO(writePaperEntity);
    }

    @Override
    public void generatePaper(String connectId,GeneratePaperCommand command) {

        WritePaperEntity writePaperEntity = writePaperRepository.find(command.getPaperId());
        if (ObjectUtil.isNull(writePaperEntity)){
            throw new ServiceException(WriteMessageConstant.GENERATE_PAPER_FAILURE);
        }

        WriteConfigEntity writeConfigEntity = writeConfigRepository.find(writePaperEntity.getConfigId());

        AIChatResponse response = openAIChatFacade.generatePaper(connectId,writePaperEntity, writeConfigEntity, command.getApiKey());
        if (ObjectUtil.isNull(response) || StrUtil.isEmpty(response.getResponseContent())) {
            throw new ServiceException(WriteMessageConstant.GENERATE_PAPER_FAILURE);
        }

        Long writeConfigStructureId = writeConfigEntity.getConfigIdByStructureName(WriteStructureEnum.PAPER.getName());
        writePaperEntity.addPaperStructure(writeConfigStructureId, response);

        writePaperRepository.modify(writePaperEntity);
    }
}
