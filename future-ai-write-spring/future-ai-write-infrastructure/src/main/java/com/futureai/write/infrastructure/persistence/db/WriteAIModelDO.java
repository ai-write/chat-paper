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
@TableName("biz_write_ai_model")
public class WriteAIModelDO {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String modelName;
    private String proxyUrl;
    private String apiKey;
    private Integer enabled;
    private Long createUser;
    private Date createTime;
    private Long updateUser;
    private Date updateTime;
    @TableLogic
    private Integer isDeleted;
}
