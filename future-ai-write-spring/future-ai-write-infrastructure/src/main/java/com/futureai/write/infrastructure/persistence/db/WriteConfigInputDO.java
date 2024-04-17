package com.futureai.write.infrastructure.persistence.db;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 表单配置表
 * </p>
 *
 * @author matf
 * @since 2024-03-08
 */
@TableName("biz_write_config_input")
@Data
public class WriteConfigInputDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 对应选项id
     */
    private Long configId;


    /**
     * 配置名
     */
    private String name;

    /**
     * 配置描述
     */
    private String description;

    /**
     * 表单类型
     */
    private String formType;

    /**
     * 数据来源
     */
    private String apiSign;

    /**
     * 表单主键
     */
    private String formKey;

    /**
     * 提示语
     */
    private String tip;

    /**
     * 响应式适配
     */
    private String span;

    /**
     * 验证规则
     */
    private String rule;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Long updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否已删除
     */
    private Integer isDeleted;

}
