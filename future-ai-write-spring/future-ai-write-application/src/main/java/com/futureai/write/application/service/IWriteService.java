package com.futureai.write.application.service;

import com.futureai.write.application.model.command.GenerateOutlineCommand;
import com.futureai.write.application.model.command.GeneratePaperCommand;
import com.futureai.write.application.model.dto.GenerateOutlineDTO;
import com.futureai.write.application.model.dto.WriteConfigDetailDTO;
import com.futureai.write.application.model.dto.WriteConfigListDTO;

import java.util.List;

/**
 * 论文写作应用服务接口
 */
public interface IWriteService {

    /**
     * 获取写作配置数据
     *
     * @return
     */
    List<WriteConfigListDTO> getWriteConfig();

    /**
     * 根据写作配置键获取配置内容
     *
     * @param configKey 配置键
     * @return
     */
    WriteConfigDetailDTO getWriteConfigInput(String configKey);

    /**
     * 论文大纲生成
     *
     * @param command Command
     * @return
     */
    GenerateOutlineDTO generateOutline(GenerateOutlineCommand command);

    /**
     * 论文全文生成
     *
     * @param connectId connectId
     * @param command Command
     */
    void generatePaper(String connectId, GeneratePaperCommand command);
}
