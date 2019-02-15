package com.zqkh.webapi.context.domain.dto.program;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zqkh.healthy.feign.vo.program.ProgramFeedbackTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 方案反馈按钮列表DTO
 *
 * @author 东来
 * @create 2018/7/4 0004
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("方案反馈按钮列表")
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgramFeedbackButtonListDto implements Serializable {

    /**
     * 按钮名称
     */
    @ApiModelProperty(value = "按钮名称", name = "name", dataType = "String", required = true)
    private String name;

    /**
     * 副标题
     */
    @ApiModelProperty(value = "副标题", name = "title", dataType = "String", required = true)
    private String title;

    /**
     * 按钮类型
     */
    @ApiModelProperty(value = "按钮类型", name = "type", dataType = "ProgramFeedbackTypeEnum", required = true)
    private ProgramFeedbackTypeEnum type;

    /**
     * 反馈内容
     */
    @ApiModelProperty(value = "反馈内容", name = "feedback", dataType = "String", required = true)
    private String feedback;

}
