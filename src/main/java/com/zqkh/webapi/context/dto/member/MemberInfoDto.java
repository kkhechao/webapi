package com.zqkh.webapi.context.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDto {

    private String userId;
    private String agencyLevel;
    private String createTime;
    private String dueTime;
}
