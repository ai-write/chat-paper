package com.futureai.write.types.dp;

import cn.hutool.core.util.StrUtil;
import com.futureai.write.common.exception.InvalidParameterException;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author max
 * @description 键值对
 * @date 2024/3/14 11:51
 */
@Data
@NoArgsConstructor
public class KeyValue {

    public KeyValue(String key, String value) {
        if (StrUtil.isEmpty(key)){
            throw new InvalidParameterException("Key 不得为空");
        }
        if (StrUtil.isEmpty(value)){
            throw new InvalidParameterException("Value 不得为空");
        }
        this.key = key;
        this.value = value;
    }

    public String key;

    public String value;
}
