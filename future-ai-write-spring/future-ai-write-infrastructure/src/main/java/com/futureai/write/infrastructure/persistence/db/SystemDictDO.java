package com.futureai.write.infrastructure.persistence.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author max
 * @description 系统字典表
 * @date 2024/3/5 17:08
 */
@Data
@TableName("biz_system_dict")
public class SystemDictDO {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private Long parentId;
    private String code;
    private String dictKey;
    private String dictValue;
    private String dictData;
    private String sort;

    @TableLogic
    private String isDeleted;
}
