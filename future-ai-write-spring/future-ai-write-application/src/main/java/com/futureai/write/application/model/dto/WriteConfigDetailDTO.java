package com.futureai.write.application.model.dto;

import com.futureai.write.types.dp.KeyValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author max
 * @description 写作配置详情DTO
 * @date 2024/3/18 16:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WriteConfigDetailDTO {

    /**
     * 材料清单
     */
    private List<KeyValue> materialList;

    /**
     * 表单输入内容
     */
    private List<Input> inputs;

    @Data
    @AllArgsConstructor
    public static class Input{
        /**
         * 配置名
         */
        private String name;

        /**
         * 配置描述
         */
        private String description;

        /**
         * 表单类型
         */
        private String formType;

        /**
         * 数据来源
         */
        private String apiSign;

        /**
         * 表单主键
         */
        private String formKey;

        /**
         * 提示语
         */
        private String tip;

        /**
         * 响应式适配
         */
        private String span;

        /**
         * 校验规则
         */
        private String rule;

        /**
         * 字典数据
         */
        private List<SystemDictTreeDTO> dictData;
    }
}
