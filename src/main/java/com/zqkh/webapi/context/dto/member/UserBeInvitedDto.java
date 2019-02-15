package com.zqkh.webapi.context.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBeInvitedDto {

    private String nickName;
    private String avatar;
    private String currentLevel;
    private String userId;
}
