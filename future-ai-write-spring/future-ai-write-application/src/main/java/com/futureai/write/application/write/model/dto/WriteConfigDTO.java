package com.futureai.write.application.write.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author max
 * @description 写作配置DTO
 * @date 2024/3/6 16:56
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WriteConfigDTO {
    private Long id;
    private Integer isDefault;
    private String configName;
    private String configKey;
    private String configIcon;

}
