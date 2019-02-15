package com.zqkh.webapi.context.domain.vo.program;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 东来
 * @create 2018/6/6 0006
 */
@NoArgsConstructor
@Getter
@Setter
@ApiModel(description = "方案反馈")
public class ProgramFeedbackVo implements Serializable {

    @ApiModel(description = "类型")
    public enum Type {

        /**
         * 难以坚持,放弃
         */
        GIVE_UP,

        /**
         * 变化不大,徘徊
         */
        WANDER,
        /**
         * 状态更好
         */
        BETTER_STATE;

    }

    /**
     * 反馈用户
     */
    @ApiModelProperty(value = "家庭成员编号", name = "familyMemberId", dataType = "String", required = true)
    private String familyMemberId;

    /**
     * 方案编号
     */
    @ApiModelProperty(value = "方案编号", name = "programId", dataType = "String", required = true)
    private String programId;

    /**
     * 反馈时间时间戳
     */
    @ApiModelProperty(value = "反馈时间时间戳", name = "time", dataType = "Long", required = true)
    private Long time;

    /**
     * 反馈类型
     */
    @ApiModelProperty(value = "反馈类型", name = "type", dataType = "Type", required = true)
    private Type type;


}
