package com.zqkh.webapi.context.domain.vo.program;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 打开方案
 *
 * @author 东来
 * @create 2018/6/12 0012
 */
@ApiModel(description = "开启方案")
@NoArgsConstructor
@Getter
@Setter
public class OpenProgramVo implements Serializable {

    @ApiModelProperty(value = "方案编号", name = "id", dataType = "String", required = true)
    private String id;
    @ApiModelProperty(value = "家庭成员编号", name = "familyMemberId", dataType = "String", required = true)
    private String familyMemberId;

}
