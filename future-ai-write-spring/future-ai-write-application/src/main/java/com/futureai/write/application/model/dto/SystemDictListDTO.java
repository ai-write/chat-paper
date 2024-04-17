package com.futureai.write.application.model.dto;

import com.futureai.write.types.dp.Dict;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author max
 * @description 列表字典DTO
 * @date 2024/3/7 11:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemDictListDTO {
    private Dict dict;
}
