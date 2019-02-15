package com.zqkh.webapi.context.dto.member;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class InvitedUserDto {

    private String nickName;
    private String phone;
    private Date regTime;
    private String avatar;
    private String currentLevel;
    private List<InvitedMemberDto> cccList;


}
