package com.futureai.write.domain.entity;

import lombok.*;

import java.util.Date;

/**
 * @author max
 * @description 写作论文明细实体
 * @date 2024/3/15 22:56
 */
@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class WritePaperStructureEntity {

    public WritePaperStructureEntity(Long structureId, String data, Long executeTime, Date startTime, Date finishTime) {
        this.structureId = structureId;
        this.data = data;
        this.executeTime = executeTime;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    /**
     * 主键Id
     */
    @Setter(AccessLevel.PUBLIC)
    private Long id;

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
}
