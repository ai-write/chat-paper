package com.futureai.write.application.assembler;

import com.futureai.write.application.model.dto.GenerateOutlineDTO;
import com.futureai.write.common.node.ForestNodeMerger;
import com.futureai.write.domain.entity.WritePaperEntity;
import com.futureai.write.types.dp.PaperOutlineTree;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author max
 * @description 论文写作转换
 * @date 2024/3/17 18:00
 */
@Component
public class WritePaperAssembler {

    public GenerateOutlineDTO toGenerateOutlineDTO(WritePaperEntity paperEntity){
        GenerateOutlineDTO dto = new GenerateOutlineDTO();

        List<PaperOutlineTree> treeList = paperEntity.getOutlines();
        treeList = ForestNodeMerger.merge(treeList);

        dto.setPaperId(paperEntity.getId());
        dto.setOutlines(treeList);

        return dto;
    }
}
