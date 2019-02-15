package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.member.feign.dto.CreatVipOrderDto;
import com.zqkh.member.feign.dto.IncomeStatisticDto;
import com.zqkh.member.feign.dto.MemberOrderDto;

public interface MemberService {

    IncomeStatisticDto rewardStatic(String useId);

    PageResult getInvitingHistoryList(String useId, int pageIndex, int pageSize);

    public MemberOrderDto creatOrder(CreatVipOrderDto creatVipOrderDto);
}
