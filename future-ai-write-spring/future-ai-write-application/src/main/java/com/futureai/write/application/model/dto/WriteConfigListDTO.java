package com.futureai.write.application.model.dto;

import com.futureai.write.types.dp.KeyValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author max
 * @description 写作配置列表DTO
 * @date 2024/3/7 16:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WriteConfigListDTO {
    private String configName;

    private String configKey;

    private String configIcon;

    private Integer isDefault;

}
