package com.futureai.write.application.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.futureai.write.common.node.INode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author max
 * @description 字典树DTO
 * @date 2024/3/7 12:03
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SystemDictTreeDTO implements INode {

    public SystemDictTreeDTO(){
        this.children = new ArrayList<>();
    }

    @JsonIgnore
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonIgnore
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    private String label;

    private String value;

    private List<SystemDictTreeDTO> children;
}
