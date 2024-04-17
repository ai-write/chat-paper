package com.futureai.write.domain.entity;

import lombok.*;

/**
 * @author max
 * @description 写作AI模型领域实体
 * @date 2024/3/6 14:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class WriteAIModelEntity {

    @Setter(AccessLevel.PUBLIC)
    private Long id;
    private String modelName;
    private String proxyUrl;

    @Setter(AccessLevel.PUBLIC)
    private String apiKey;

}
