package com.futureai.write.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author max
 * @description 系统字典实体类
 * @date 2024/3/6 14:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemDictEntity {


    private Long id;

    private Long parentId;

    private String code;

    private String dictKey;

    private String dictValue;

    private String dictData;

}