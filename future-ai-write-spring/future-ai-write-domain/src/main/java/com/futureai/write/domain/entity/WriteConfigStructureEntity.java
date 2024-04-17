package com.futureai.write.domain.entity;

import cn.hutool.core.util.StrUtil;
import com.futureai.write.types.dp.KeyValue;
import lombok.*;

import java.util.List;

/**
 * @author max
 * @description 写作配置结构实体
 * @date 2024/3/6 15:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class WriteConfigStructureEntity {
    private Long id;
    private String structureName;
    private Long structureModelId;
    private String structurePrompt;

    /**
     * 格式化论文结构提示词
     * @param params 参数
     */
    public void formatStructurePrompt(List<KeyValue> params){
        for (KeyValue param : params) {
            this.structurePrompt =  StrUtil.replace(this.structurePrompt, "${" + param.getKey() + "}", param.getValue());
        }
    }
}
