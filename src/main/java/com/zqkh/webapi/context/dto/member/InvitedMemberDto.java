package com.zqkh.webapi.context.dto.member;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class InvitedMemberDto {


    private String level;
    private Date upTime;
    private BigDecimal money;

}
