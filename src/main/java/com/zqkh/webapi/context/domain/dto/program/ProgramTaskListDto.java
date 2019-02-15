package com.zqkh.webapi.context.domain.dto.program;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 方案任务列表DTO
 *
 * @author 东来
 * @create 2018/6/6 0006
 */
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "方案任务")
public class ProgramTaskListDto implements Serializable {


    /**
     * 编号
     */
    @ApiModelProperty(value = "任务编号", name = "id", dataType = "String", required = true)
    private String id;

    /**
     * 投放日期
     */
    @ApiModelProperty(value = "投放日期", name = "time", dataType = "Date", required = true)
    private Date time;

    /**
     * 是否完成
     */
    @ApiModelProperty(value = "是否完成", name = "done", dataType = "Date", required = true)
    private Boolean done;


    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称", name = "name", dataType = "Date", required = true)
    private String name;

    /**
     * 任务说明
     */
    @ApiModelProperty(value = "任务说明", name = "desc", dataType = "String", required = true)
    private String desc;


}
