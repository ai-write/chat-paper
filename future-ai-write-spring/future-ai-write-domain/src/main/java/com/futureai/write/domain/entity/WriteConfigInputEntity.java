package com.futureai.write.domain.entity;

import lombok.*;

import java.util.List;

/**
 * @author matf
 * @create 2024-03-08
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class WriteConfigInputEntity {
        /**
         * 主键
         */
        private Long id;

        /**
         * 对应选项id
         */
        private Long configId;

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

}
