package com.zqkh.webapi.context.domain.vo.wemedia.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 问题页面对象
 *
 * @author 悭梵
 * @date Created in 2018-06-20 15:55
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@ApiModel(description = "提问")
public class QuestionVo implements Serializable {
    /**
     * 内容
     */
    @ApiModelProperty(value = "问题", name = "content", required = true, dataType = "String")
    private String content;
    /**
     * 描述
     */
    @ApiModelProperty(value = "问题描述", name = "description", dataType = "String")
    private String description;
    /**
     * 图片材料
     */
    @ApiModelProperty(value = "问题图片标识", name = "fileUrlList", dataType = "String")
    private List<String> fileUrlList;
}
