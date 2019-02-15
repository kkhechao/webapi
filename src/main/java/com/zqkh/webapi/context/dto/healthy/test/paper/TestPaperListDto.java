package com.zqkh.webapi.context.dto.healthy.test.paper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zqkh.webapi.context.domain.dto.test.paper.TestPaperDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 试卷列表
 *
 * @author 东来
 * @create 2018/5/9 0009
 */
@ApiModel("试卷列表")
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestPaperListDto implements Serializable {

    /**
     * 推荐测试
     */
    @ApiModelProperty(value = "推荐测试", name = "recommend", dataType = "TestPaperDto", required = true)
    private List<TestPaperDto> recommend;

    /**
     * 其他测题
     */
    @ApiModelProperty(value = "其他测题", name = "other", dataType = "TestPaperDto", required = true)
    private List<TestPaperDto> other;

}
