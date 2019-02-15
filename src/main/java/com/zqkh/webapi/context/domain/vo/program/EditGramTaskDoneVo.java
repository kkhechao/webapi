package com.zqkh.webapi.context.domain.vo.program;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 修改方案任务完成状态VO
 *
 * @author 东来
 * @create 2018/6/12 0012
 */
@ApiModel("修改方案任务完成状态")
@NoArgsConstructor
@Getter
@Setter
public class EditGramTaskDoneVo implements Serializable {
    @ApiModelProperty(value = "方案任务编号", name = "id", dataType = "String", required = true)
    private String id;

    @ApiModelProperty(value = "家庭成员编号", name = "familyMemberId", dataType = "String", required = true)
    private String familyMemberId;

    @ApiModelProperty(value = "是否完成", name = "done", dataType = "String", required = true)
    private Boolean done;
}
