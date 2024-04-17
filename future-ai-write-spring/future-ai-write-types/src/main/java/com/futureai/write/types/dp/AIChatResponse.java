package com.futureai.write.types.dp;

import lombok.Data;

import java.util.Date;

/**
 * @author max
 * @description AI对话响应
 * @date 2024/3/14 18:13
 */
@Data
public class AIChatResponse {

    private Long executeTime;

    private Date startTime;

    private Date finishTime;

    private String responseContent;
}
