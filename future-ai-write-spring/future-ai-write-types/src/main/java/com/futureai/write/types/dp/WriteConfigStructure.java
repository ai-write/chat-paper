package com.futureai.write.types.dp;

import lombok.Data;

/**
 * @author max
 * @description 写作配置DP
 * @date 2024/3/17 11:02
 */
@Data
public class WriteConfigStructure {

    /**
     * 论文结构配置名称
     */
    private String structureConfigName;

    /**
     * 论文结构提示词
     */
    private String structureConfigPrompt;
}
