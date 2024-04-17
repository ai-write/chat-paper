package com.futureai.write.application.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.futureai.write.types.dp.PaperOutlineTree;
import lombok.Data;

import java.util.List;

/**
 * @author max
 * @description 论文大纲生成DTO
 * @date 2024/3/14 14:03
 */
@Data
public class GenerateOutlineDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long paperId;
    List<PaperOutlineTree> outlines;
}
