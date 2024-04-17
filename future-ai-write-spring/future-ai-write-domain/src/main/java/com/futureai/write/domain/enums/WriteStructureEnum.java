package com.futureai.write.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author max
 * @description 写作结构枚举
 * @date 2024/3/14 17:11
 */
@Getter
@AllArgsConstructor
public enum WriteStructureEnum {
    OUTLINE("outline","大纲"),
    PAPER("paper","全文"),
    ;
    private final String name;
    private final String desc;
}
