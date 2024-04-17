package com.futureai.write.application.service;

import com.futureai.write.application.model.dto.SystemDictListDTO;
import com.futureai.write.application.model.dto.SystemDictTreeDTO;

import java.util.List;

/**
 * @author max
 * @description 系统字典应用服务接口
 * @date 2024/3/7 12:00
 */
public interface ISystemDictService {

    /**
     * 获取字典列表
     * @param code 字典码
     * @return
     */
    List<SystemDictListDTO> list(String code);

    /**
     * 获取字典树
     * @param code 字典码
     * @return
     */
    List<SystemDictTreeDTO> tree(String code);

}
