package com.futureai.write.infrastructure.persistence.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author max
 * @description 论文记录明细DO
 * @date 2024/3/5 17:14
 */
@Data
@TableName("biz_write_paper_structure")
public class WritePaperStructureDO {

    /**
     * 主键Id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 论文记录Id
     */
    private Long paperId;

    /**
     * 论文结构Id
     */
    private Long structureId;

    /**
     * 论文结构数据
     */
    private String data;

    /**
     * 执行时间
     */
    private Long executeTime;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date finishTime;

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
