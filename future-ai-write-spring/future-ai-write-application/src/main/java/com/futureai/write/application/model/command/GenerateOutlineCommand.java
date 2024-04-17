package com.futureai.write.application.model.command;

import com.futureai.write.types.dp.KeyValue;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author max
 * @description 论文大纲生成Command
 * @date 2024/3/14 11:41
 */
@Data
public class GenerateOutlineCommand {

   @NotEmpty
   private String configKey;

   private String apiKey;

   private List<KeyValue> params;
}
