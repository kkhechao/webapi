package com.zqkh.webapi.context.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardListDto {

    List<UserBeInvitedDto> userBeInvitedDtoList;
    /**
     * 距离达标人数
     */
    private Integer needNum;
    /**
     * 达标人数
     */
    private  Integer standardNum;
    /**
     * 当前等级
     */
    private String agenceLevel;
}
