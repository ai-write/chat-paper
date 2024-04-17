package com.futureai.write.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author max
 * @description 论文生成状态枚举
 * @date 2024/3/17 11:19
 */
@Getter
@AllArgsConstructor
public enum WritePaperGenerateStatusEnum {

    SUBMITTED(0,"已经提交"),
    GENERATING(1,"生成中"),
    GENERATED(2,"生成成功"),
    EXCEPTION(-1,"异常"),
    ;

    /**
     * 状态
     */
    private final Integer status;

    /**
     * 描述
     */
    private final String description;
}
