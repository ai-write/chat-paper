package com.futureai.write.infrastructure.persistence.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author max
 * @description AI论文写作配置DO类
 * @date 2024/3/5 17:14
 */
@Data
@TableName("biz_write_config")
public class WriteConfigDO {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String configName;
    private String configKey;
    private String configIcon;
    private String configData;
    /**
     * 材料清单
     */
    private String materialList;
    private Integer isDefault;
    private Integer sort;
    private Long createUser;
    private Date createTime;
    private Long updateUser;
    private Date updateTime;
    @TableLogic
    private Integer isDeleted;
}
