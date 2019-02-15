package com.zqkh.webapi.context.dto.healthy.test.paper;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 家庭成员答题结果
 *
 * @author 东来
 * @create 2018/5/10 0010
 */
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("答题结果")
public class FamilyMemberTestResultDto implements Serializable {

    /**
     * 第几题
     */
    @ApiModelProperty(value = "第几题", name = "index", dataType = "int", required = true)
    private Integer index;

    /**
     * 选项名
     */
    @ApiModelProperty(value = "选项名", name = "optionName", dataType = "String")
    private String optionName;


    /**
     * 上一题
     */
    @ApiModelProperty(value = "上一题", name = "previousIndex", dataType = "String")
    private Integer previousIndex;

}
