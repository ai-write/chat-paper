package com.futureai.write.types.dp;

import cn.hutool.core.util.StrUtil;
import com.futureai.write.common.exception.InvalidParameterException;
import lombok.Data;

/**
 * @author max
 * @description 字典DP
 * @date 2024/3/7 11:55
 */
@Data
public class Dict {
    public Dict(String label, String value) {
        if (StrUtil.isEmpty(label)) {
            throw new InvalidParameterException("label不得为空");
        }
        if (StrUtil.isEmpty(value)) {
            throw new InvalidParameterException("value不得为空");
        }
        this.label = label;
        this.value = value;
    }

    private String label;

    private String value;
}
