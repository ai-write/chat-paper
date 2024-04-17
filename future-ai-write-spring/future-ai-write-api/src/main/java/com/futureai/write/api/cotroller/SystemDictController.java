package com.futureai.write.api.cotroller;

import com.futureai.write.common.api.R;
import com.futureai.write.application.model.dto.SystemDictListDTO;
import com.futureai.write.application.model.dto.SystemDictTreeDTO;
import com.futureai.write.application.service.ISystemDictService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author max
 * @description 系统字段控制器
 * @date 2024/3/7 10:51
 */
@RestController
@AllArgsConstructor
@RequestMapping("system/dict")
public class SystemDictController {

    private ISystemDictService systemDictService;

    @GetMapping("list/{code}")
    public R<List<SystemDictListDTO>> list(@PathVariable(name = "code") String code) {
        List<SystemDictListDTO> list = systemDictService.list(code);
        return R.data(list);
    }

    @GetMapping("tree/{code}")
    public R<List<SystemDictTreeDTO>> tree(@PathVariable(name = "code") String code) {
        List<SystemDictTreeDTO> list = systemDictService.tree(code);
        return R.data(list);
    }
}
