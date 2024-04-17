package com.futureai.write.application.model.command;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author max
 * @description 论文全文生成Command
 * @date 2024/3/19 11:14
 */
@Data
public class GeneratePaperCommand {

    private String apiKey;

    /**
     * 论文Id
     */
    private Long paperId;
}
