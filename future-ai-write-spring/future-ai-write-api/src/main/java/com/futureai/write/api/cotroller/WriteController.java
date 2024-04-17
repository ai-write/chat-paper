package com.futureai.write.api.cotroller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.futureai.write.application.model.command.GeneratePaperCommand;
import com.futureai.write.common.api.R;
import com.futureai.write.application.model.command.GenerateOutlineCommand;
import com.futureai.write.application.model.dto.GenerateOutlineDTO;
import com.futureai.write.application.model.dto.WriteConfigDetailDTO;
import com.futureai.write.application.model.dto.WriteConfigListDTO;
import com.futureai.write.application.service.IWriteService;
import com.futureai.write.common.handler.pool.CacheSSEPool;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

/**
 * @author max
 * @description AI写作控制器
 * @date 2024/3/7 16:52
 */
@RestController
@RequestMapping("write")
@AllArgsConstructor
public class WriteController {

    private final IWriteService writeService;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @GetMapping("/config/list")
    public R<List<WriteConfigListDTO>> getWriteConfigList() {
        return R.data(writeService.getWriteConfig());
    }

    @GetMapping("/config/detail")
    public R<WriteConfigDetailDTO> getWriteConfigDetail(String configKey) {
        return R.data(writeService.getWriteConfigInput(configKey));
    }

    @PostMapping("/generate/outline")
    public R<GenerateOutlineDTO> generateOutline(@Validated @RequestBody GenerateOutlineCommand command) {
        GenerateOutlineDTO dto = writeService.generateOutline(command);
        return R.data(dto);
    }

    @PostMapping("/generate/paper")
    public SseEmitter generatePaper(@Validated @RequestBody GeneratePaperCommand command) {
        SseEmitter sse = new SseEmitter(0L);
        String sseId = IdUtil.fastSimpleUUID();
        sse.onCompletion(() -> CacheSSEPool.remove(sseId));
        CacheSSEPool.add(sseId, sse);

        taskExecutor.execute(() -> {
            try {
                writeService.generatePaper(sseId, command);
            } catch (Exception e) {
                try {
                    if (ObjectUtil.isNotNull(sse)) {
                        sse.send(SseEmitter.event().data(R.fail(e.getMessage())));
                        sse.complete();
                        CacheSSEPool.remove(sseId);
                    }

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        return sse;
    }
}
