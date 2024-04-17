package com.futureai.write.domain.entity;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.futureai.write.domain.enums.WritePaperGenerateStatusEnum;
import com.futureai.write.types.constant.WriteMessageConstant;
import com.futureai.write.types.dp.AIChatResponse;
import com.futureai.write.types.dp.PaperOutlineTree;
import com.futureai.write.common.exception.ServiceException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author max
 * @description 论文写作实体
 * @date 2024/3/15 14:15
 */
@Data
@AllArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class WritePaperEntity {

    public WritePaperEntity(Long configId, String detail, WritePaperGenerateStatusEnum generateStatusEnum, Date submitTime) {
        this.configId = configId;
        this.detail = detail;
        this.generateStatus = generateStatusEnum.getStatus();
        this.submitTime = submitTime;
        this.structures = new ArrayList<>();
    }

    public WritePaperEntity(Long id, Long configId, String title, String detail, Integer generateTime, String content, String ossUrl, Integer generateStatus, Date submitTime, Date completeTime, List<WritePaperStructureEntity> structures) {
        this.id = id;
        this.configId = configId;
        this.title = title;
        this.detail = detail;
        this.generateTime = generateTime;
        this.content = content;
        this.ossUrl = ossUrl;
        this.generateStatus = generateStatus;
        this.submitTime = submitTime;
        this.completeTime = completeTime;
        this.structures = structures;
    }

    /**
     * 论文Id
     */
    @Setter(AccessLevel.PUBLIC)
    private Long id;

    /**
     * 论文标题
     */
    private String title;

    /**
     * 写作配置Id
     */
    private Long configId;

    /**
     * 写作属性明细
     */
    private String detail;

    /**
     * 生成时间
     */
    private Integer generateTime;

    /**
     * 论文全文内容
     */
    private String content;

    /**
     * 论文OSS链接
     */
    private String ossUrl;

    /**
     * 生成状态
     */
    private Integer generateStatus;

    /**
     * 生成提交时间
     */
    private Date submitTime;

    /**
     * 论文生成完成时间
     */
    private Date completeTime;

    /**
     * 论文结构明细
     */
    private List<WritePaperStructureEntity> structures;

    /**
     * 论文大纲
     */
    public List<PaperOutlineTree> outlines;

    /**
     * 添加论文明细数据
     *
     * @param structureId 论文结构Id
     * @param response    AI模型返回数据
     */
    public void addPaperStructure(Long structureId, AIChatResponse response) {
        WritePaperStructureEntity structureEntity = new WritePaperStructureEntity(
                structureId,
                response.getResponseContent(),
                response.getExecuteTime(),
                response.getStartTime(),
                response.getFinishTime()
        );

        this.structures.add(structureEntity);
    }

    /**
     * 根据大纲配置结构Id格式化大纲
     * @param outlineStructureId Id
     */
    public void formatOutlineFromStructure(Long outlineStructureId){
        if (CollUtil.isEmpty(this.structures)){
            return;
        }
        WritePaperStructureEntity outlineStructureEntity = this.structures.stream()
                .filter(structure -> structure.getStructureId().equals(outlineStructureId))
                .findFirst()
                .orElse(null);

        if (ObjectUtil.isNull(outlineStructureEntity)){
            return;
        }

        this.formatOutlineFromJson(outlineStructureEntity.getData());
    }

    /**
     * 从Json反序列化大纲
     *
     * @param jsonStr Json字符串
     */
    public void formatOutlineFromJson(String jsonStr) {
        try {
            JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
            this.title = jsonObject.getStr("title", StrUtil.EMPTY);
            List<PaperOutlineTree> outlines = JSONUtil.toList(jsonObject.getStr("outlines"), PaperOutlineTree.class);
            if (CollUtil.isEmpty(outlines)) {
                throw new ServiceException(WriteMessageConstant.GENERATE_OUTLINE_FAILURE);
            }
            this.outlines = outlines;
        } catch (Exception e) {
            throw new ServiceException(WriteMessageConstant.GENERATE_OUTLINE_FAILURE);
        }
    }
}
