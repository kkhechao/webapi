package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.result.PageResult;
import com.zqkh.member.feign.MemberClient;
import com.zqkh.member.feign.dto.*;
import com.zqkh.user.feign.UserClient;
import com.zqkh.user.feign.dto.InvitedPeopleInfoDto;
import com.zqkh.user.feign.dto.UserInfoRequest;
import com.zqkh.webapi.context.dto.member.InvitedMemberDto;
import com.zqkh.webapi.context.dto.member.InvitedUserDto;
import com.zqkh.webapi.context.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MemberServiceImpl implements MemberService {

    public static final String MEMBER = "MEMBER";
    @Autowired
    private MemberClient memberClient;

    @Autowired
    private UserClient userClient;

    @Override
    public IncomeStatisticDto rewardStatic(String useId) {
        return memberClient.getIncomeStatistics(useId);
    }

    @Override
    public PageResult getInvitingHistoryList(String useId, int pageIndex, int pageSize) {

        //返回对象封装
        PageResult pageResult = new PageResult();

        //获取用户 ids
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        userInfoRequest.setPageSize(pageSize);
        userInfoRequest.setPageIndex(pageIndex);
        userInfoRequest.setUserId(useId);
        PageResult<InvitedPeopleInfoDto> invitedUserInfo = userClient.getInvitedUserInfo(userInfoRequest);

        List<InvitedPeopleInfoDto> invitedPeopleInfoDtos = invitedUserInfo.getList();
        if (invitedPeopleInfoDtos != null && invitedPeopleInfoDtos.size() > 0) {
            List<String> ids = invitedPeopleInfoDtos.stream().map(user -> user.getId()).collect(Collectors.toList());
            if (ids != null && ids.size() > 0) {
                String[] userIds = ids.toArray(new String[]{});
//            //获取用户级别
//            List<MemberDto> memberInfo = memberClient.getMemberInfo(userIds);
//            if (memberInfo == null || memberInfo.size() == 0) {
//                pageResult.setTotalCount(invitedUserInfo.getTotalCount());
//                pageResult.setPageSize(invitedUserInfo.getPageSize());
//                pageResult.setList(invitedPeopleInfoDtos);
//                return pageResult;
//            }
                //获取代理奖励记录
                List<UpAgentRecordDto> agentHistoryList = memberClient.getAgentHistoryList(useId, userIds);
                //获取奖励记录
                List<RewardRecordDto> invitingHistoryList = memberClient.getInvitingHistoryList(useId, userIds);

                List<InvitedUserDto> collect = invitedPeopleInfoDtos.stream().map(p -> {
                    InvitedUserDto invitedUser = new InvitedUserDto();
                    invitedUser.setNickName(p.getNickName());
                    invitedUser.setPhone(p.getPhone());
                    invitedUser.setRegTime(p.getCreateTime());
                    invitedUser.setAvatar(p.getAvatar());
                    invitedUser.setCurrentLevel(/*memberInfo.stream()
                        .filter(q -> q.getUserId().equals(p.getId()))
                        .findFirst()
                        .orElse(null)
                        .getAgencyLevel());*/
                            p.getLevel());
                    invitedUser.setCccList(
                            invitingHistoryList.stream()
                                    .filter(k -> k.getRecommended().equals(p.getId()) && k.getIntroducer().equals(useId))
                                    .map(k -> {
                                        InvitedMemberDto invitedMember = new InvitedMemberDto();

                                        invitedMember.setLevel(MEMBER);
                                        invitedMember.setMoney(k.getRewardAmount());
                                        invitedMember.setUpTime(k.getCreateTime());
                                        return invitedMember;
                                    })
                                    .collect(Collectors.toList())

                    );
                    List<InvitedMemberDto> collect1 = agentHistoryList.stream()
                            .filter(m -> m.getMemberId().equals(p.getId()) && m.getIntroducer().equals(useId))
                            .map(m -> {
                                InvitedMemberDto invitedMember = new InvitedMemberDto();
                                invitedMember.setLevel(m.getMemberUpToLevel().toUpperCase());
                                invitedMember.setMoney(m.getUpBount());
                                invitedMember.setUpTime(m.getCreateTime());
                                return invitedMember;
                            })
                            .collect(Collectors.toList());
                    invitedUser.getCccList().addAll(collect1);
                    return invitedUser;
                }).collect(Collectors.toList());


                pageResult.setTotalCount(invitedUserInfo.getTotalCount());
                pageResult.setPageSize(invitedUserInfo.getPageSize());
                pageResult.setList(collect);

                return pageResult;

            }

        }
        pageResult.setTotalCount(0);
        pageResult.setPageSize(0);
        pageResult.setList(new ArrayList());
        return pageResult;
    }

    @Override
    public MemberOrderDto creatOrder(CreatVipOrderDto creatVipOrderDto) {
        return memberClient.creatOrder(creatVipOrderDto);
    }


}
