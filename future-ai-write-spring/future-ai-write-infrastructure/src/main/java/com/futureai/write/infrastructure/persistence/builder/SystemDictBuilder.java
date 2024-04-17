package com.futureai.write.infrastructure.persistence.builder;

import cn.hutool.core.collection.CollUtil;
import com.futureai.write.domain.entity.SystemDictEntity;
import com.futureai.write.infrastructure.persistence.db.SystemDictDO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author max
 * @description 系统字典转换类
 * @date 2024/3/7 14:37
 */
@Component
public class SystemDictBuilder {

    public List<SystemDictEntity> toEntityList(List<SystemDictDO> doList) {
        if (CollUtil.isEmpty(doList)) {
            return new ArrayList<>();
        }

        return doList.stream()
                .map(dict -> new SystemDictEntity(
                                dict.getId(),
                                dict.getParentId(),
                                dict.getCode(),
                                dict.getDictKey(),
                                dict.getDictValue(),
                                dict.getDictData()
                        )
                ).collect(Collectors.toList());
    }
}
