package com.futureai.write.types.dp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.futureai.write.common.node.INode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author max
 * @description 论文树形大纲
 * @date 2024/3/14 16:16
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PaperOutlineTree implements INode<PaperOutlineTree> {

    public PaperOutlineTree(){
        this.children = new ArrayList<>();
    }

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    private String title;
    private Integer level;
    private String chapter;
    private String description;

    private List<PaperOutlineTree> children;
}
