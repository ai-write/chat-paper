package com.futureai.write.application.service.impl;

import com.futureai.write.application.assembler.SystemDictAssembler;
import com.futureai.write.application.model.dto.SystemDictListDTO;
import com.futureai.write.application.model.dto.SystemDictTreeDTO;
import com.futureai.write.application.service.ISystemDictService;
import com.futureai.write.domain.entity.SystemDictEntity;
import com.futureai.write.domain.repository.ISystemDictRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author max
 * @description 系统字典应用服务实现
 * @date 2024/3/7 14:29
 */
@Service
@AllArgsConstructor
public class SystemDictServiceImpl implements ISystemDictService {

    private SystemDictAssembler systemDictAssembler;
    private ISystemDictRepository systemDictRepository;

    @Override
    public List<SystemDictListDTO> list(String code) {
        List<SystemDictEntity> entityList = systemDictRepository.findListByCode(code);
        return systemDictAssembler.toDictList(entityList);
    }

    @Override
    public List<SystemDictTreeDTO> tree(String code) {
        List<SystemDictEntity> entityList = systemDictRepository.findListByCode(code);
        return systemDictAssembler.toDictTree(entityList);
    }
}
