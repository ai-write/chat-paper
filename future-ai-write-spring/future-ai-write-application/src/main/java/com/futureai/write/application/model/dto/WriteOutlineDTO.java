package com.futureai.write.application.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author max
 * @description 论文大纲DTO
 * @date 2024/3/14 01:38
 */
@Data
public class WriteOutlineDTO {

    public WriteOutlineDTO(){
        this.children = new ArrayList<>();
    }

    private Long id;
    private Long parentId;
    private String chapter;
    private String title;

    private List<WriteOutlineDTO> children;
}
