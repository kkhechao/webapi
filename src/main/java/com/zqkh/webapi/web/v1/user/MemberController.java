package com.zqkh.webapi.web.v1.user;


import com.zqkh.common.result.PageResult;
import com.zqkh.common.util.DateUtil;
import com.zqkh.item.feign.ItemClient;
import com.zqkh.item.feign.dto.ItemDto;
import com.zqkh.member.feign.MemberClient;
import com.zqkh.member.feign.dto.*;
import com.zqkh.order.feign.dto.AddressInfoDto;
import com.zqkh.user.feign.UserClient;
import com.zqkh.user.feign.dto.AddressDto;
import com.zqkh.user.feign.dto.UserSimpleDto;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.dto.member.*;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/member")
public class MemberController {


    @Autowired
    UserClient userClient;

    @Autowired
    ItemClient itemClient;

    @Autowired
    MemberClient memberClient;

    @Autowired
    MemberService memberService;


    @ApiOperation(value = "api_member_getIncomeStatistics", notes = "收益统计")
    @GetMapping("/getIncomeStatistics")
    public IncomeStatisticDto getIncomeStatistics() {
        JWTUserDto userDto = AuthManager.currentUser();
        String userId = userDto.getUserId();
        return memberService.rewardStatic(userId);
    }


    @ApiOperation(value = "api_member_getInvitingHistoryList", notes = "邀请历史")
    @GetMapping("/getInvitingHistoryList")
    public PageResult getInvitingHistoryList(int pageIndex, int pageSize) {
        JWTUserDto userDto = AuthManager.currentUser();
        String userId = userDto.getUserId();
        return memberService.getInvitingHistoryList(userId, pageIndex, pageSize);
    }

    @ApiOperation(value = "api_member_creatVipOrder", notes = "创建会员订单")
    @PostMapping("/creatVipOrder")
    public MemberOrderDto creatOrder(@RequestBody VipOrderDto vipOrderDto) {
        JWTUserDto userDto = AuthManager.currentUser();
        String userId = userDto.getUserId();
        CreatVipOrderDto creatVipOrderDto = new CreatVipOrderDto();
        creatVipOrderDto.setUserId(userId);
        creatVipOrderDto.setMemberGoodsId(vipOrderDto.getMemberGoodsId());
        AddressDto addressDetailById = userClient.getAddressDetailById(userId, vipOrderDto.getAddressDetailId());
        AddressInfoDto addressInfoDto = new AddressInfoDto();
        addressInfoDto.setDetail(addressDetailById.getDetail());
        addressInfoDto.setPhone(addressDetailById.getPhone());
        addressInfoDto.setName(addressDetailById.getName());
        addressInfoDto.setCode(addressDetailById.getCode());
        creatVipOrderDto.setAddressInfoDto(addressInfoDto);
        creatVipOrderDto.setMessage(vipOrderDto.getMessage());
        return memberService.creatOrder(creatVipOrderDto);
    }


    @ApiOperation(value = "api_member_getMemberGooodsList", notes = "查询会员商品列表")
    @GetMapping("/getMemberGooodsList")
    public List<MemberGoodSDto> getMemberGooods() {
        List<MemberGoodsListDto> memberGoodsdDtoList = memberClient.getMemberGooodsId();

        String[] strings = memberGoodsdDtoList.stream().map(p -> p.getGoodsId()).toArray(String[]::new);
        List<ItemDto> itemInfoListByIds = itemClient.getMemberGoodItemInfoListByIds(strings);

        List<MemberGoodSDto> memberGoodSDtoList = new ArrayList<>();
        itemInfoListByIds.forEach(m -> {
            MemberGoodSDto memberGoodSDto = new MemberGoodSDto();
            memberGoodSDto.setGoodsId(m.getId());
            memberGoodSDto.setMallPrice(m.getMallPrice());
            memberGoodSDto.setSurfacePhoto(m.getSurfacePhoto());
            memberGoodSDto.setTitle(m.getTitle());
            memberGoodSDto.setCate(m.getCate());
            if (!ObjectUtils.isEmpty(m.getTags())) {
                memberGoodSDto.setTags(Arrays.stream(m.getTags().split(",")).collect(Collectors.toList()));
            }
            memberGoodSDto.setId(memberGoodsdDtoList.stream().filter(p -> p.getGoodsId().equals(m.getId())).findFirst().get().getId());
            memberGoodSDtoList.add(memberGoodSDto);


        });
        return memberGoodSDtoList;

    }

    @ApiOperation(value = "api_member_creatAgentOrder", notes = "创建代理订单")
    @PostMapping("/creatAgentOrder")
    public AgentOrderDto creatAgentOrder(@RequestBody CreateAgentOrderDTo createAgentOrderDTo) {
        JWTUserDto userDto = AuthManager.currentUser();
        String userId = userDto.getUserId();
        return memberClient.creatOrder(userId, createAgentOrderDTo.getLevel().toString().toLowerCase());
    }

    @ApiOperation(value = "api_member_getStandardInvitingHistoryList", notes = "达标奖励-已经邀请人员")
    @GetMapping("/getStandardInvitingHistoryList")
    public StandardListDto getStandardInvitingHistoryList() {

        StandardListDto standardListDto = new StandardListDto();
        List<UserBeInvitedDto> userBeInvitedDtoList=new ArrayList<>();
        JWTUserDto userDto = AuthManager.currentUser();
        String userId = userDto.getUserId();
        StandardDto standardDto = memberClient.getMemberListByLevel(userId);
        if(standardDto==null){
            throw new BusinessException(BusinessExceptionEnum.NO_AGENT_EXCEPTION.getCode(),BusinessExceptionEnum.NO_AGENT_EXCEPTION.getMessage());
        }
        List<MemberDto> memberDtoList = standardDto.getMemberDtoList();

        memberDtoList.forEach(p->{
            UserBeInvitedDto userBeInvitedDto = new UserBeInvitedDto();
            String pUserId = p.getUserId();
            UserSimpleDto userSimpleDto = userClient.simpleInfo(pUserId);
            userBeInvitedDto.setAvatar(userSimpleDto.getAvatar());
            userBeInvitedDto.setNickName(userSimpleDto.getNickName());
            userBeInvitedDto.setCurrentLevel(p.getAgencyLevel());
            userBeInvitedDto.setUserId(pUserId);
            userBeInvitedDtoList.add(userBeInvitedDto);
        });
        standardListDto.setUserBeInvitedDtoList(userBeInvitedDtoList);
        standardListDto.setAgenceLevel(standardDto.getAgenceLevel());
        standardListDto.setNeedNum(standardDto.getNeedNum());
        standardListDto.setStandardNum(standardDto.getStandardNum());
        return standardListDto;
    }


    /**
     * 查询会员
     *
     */
    @ApiOperation(value = "api_member_memberInfo", notes = "获取会员有效时间")
    @GetMapping("/memberInfo")
    public MemberInfoDto getMemberLevel(){
        JWTUserDto userDto = AuthManager.currentUser();
        String userId = userDto.getUserId();
        MemberInfoDto memberInfoDto = new MemberInfoDto();
        List<MemberDto> memberLevel = memberClient.getMemberLevel(new String[]{userId});
        if(memberLevel!=null&&memberLevel.size()>0){
            MemberDto memberDto = memberLevel.get(0);
            Date createTime = memberDto.getCreateTime();
            Date dueTime = memberDto.getDueTime();
            if(createTime!=null&&dueTime!=null){
                memberInfoDto.setCreateTime(DateUtil.parseDateToStr(createTime,DateUtil.DATE_FORMAT_YYYY_MM_DD));
                memberInfoDto.setDueTime( DateUtil.parseDateToStr(dueTime,DateUtil.DATE_FORMAT_YYYY_MM_DD));
            }
            memberInfoDto.setUserId(userId);
            memberInfoDto.setAgencyLevel(memberDto.getAgencyLevel());
        }
        return memberInfoDto;
    }

}
