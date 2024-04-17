package com.futureai.write.infrastructure.persistence.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author max
 * @description AI论文写作记录DO
 * @date 2024/3/5 17:14
 */
@Data
@TableName("biz_write_paper")
public class WritePaperDO {

    /**
     * 论文Id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 写作配置Id
     */
    private Long configId;

    /**
     * 论文标题
     */
    private String title;

    /**
     * 写作属性明细
     */
    private String detail;

    /**
     * 生成时间
     */
    private Integer generateTime;

    /**
     * 论文全文内容
     */
    private String content;

    /**
     * 论文OSS链接
     */
    private String ossUrl;

    /**
     * 生成状态
     */
    private Integer generateStatus;

    /**
     * 生成提交时间
     */
    private Date submitTime;

    /**
     * 论文生成完成时间
     */
    private Date completeTime;
    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private Long updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDeleted;
}
