package com.zqkh.webapi.context.dto.member;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VipOrderDto {


    private String memberGoodsId;
    @NotNull
    private String addressDetailId;

    private String message;

    private Boolean integral;
}

