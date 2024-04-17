package com.futureai.write.infrastructure.persistence.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author max
 * @description 系统配置DO
 * @date 2024/3/5 17:08
 */
@Data
@TableName("biz_system_config")
public class SystemConfigDO {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String category;
    private String configKey;
    private String configValue;
    private Long createUser;
    private Date createTime;
    private Long updateUser;
    private Date updateTime;
    @TableLogic
    private Integer isDeleted;
}
