package com.zqkh.webapi.context.service.impl;

import com.zqkh.common.result.PageResult;
import com.zqkh.item.feign.ItemClient;
import com.zqkh.item.feign.dto.ItemInfoToAppDto;
import com.zqkh.item.feign.dto.ItemListToAppDto;
import com.zqkh.item.feign.dto.tag.ItemTagListToAppDto;
import com.zqkh.member.feign.MemberClient;
import com.zqkh.member.feign.dto.MemberDto;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.AppItemService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * App商品业务实现层
 * @author 东来
 * @create 2018/1/19 0019
 */
@Service
public class AppItemServiceImpl implements AppItemService {

    @Resource
    private ItemClient itemClient;


    @Resource
    private MemberClient memberClient;


    @Override
    public PageResult<ItemListToAppDto> getItemListToApp(Integer pageIndex, Integer pageSize,String catalogueId,String tag) {

        PageResult<ItemListToAppDto> itemListToAppDtoPageResult=itemClient.getItemListToApp(pageIndex,pageSize,catalogueId,tag);
        if (!ObjectUtils.isEmpty(itemListToAppDtoPageResult)&&!ObjectUtils.isEmpty(itemListToAppDtoPageResult.getList())) {
            JWTUserDto userDto = AuthManager.currentUser();
            if (!ObjectUtils.isEmpty(userDto)) {
                MemberDto member = memberClient.getMember(userDto.getUserId());
                if (!ObjectUtils.isEmpty(member)) {
                    itemListToAppDtoPageResult.getList().forEach(n->{
                        if(!ObjectUtils.isEmpty(n.getVipPrice())){
                            n.setMallPrice(n.getVipPrice());
                        }
                    });
                }
            }
        }
        return itemListToAppDtoPageResult;
    }

    @Override
    public ItemInfoToAppDto getItemInfoDtoToApp(String id) {
        return itemClient.getItemInfoDtoToApp(id);
    }

    @Override
    public List<ItemTagListToAppDto> getItemTagList() {
        List<ItemTagListToAppDto> itemTagListToAppDtos=itemClient.getItemTagInfo();
        if(!ObjectUtils.isEmpty(itemTagListToAppDtos)){
            JWTUserDto userDto = AuthManager.currentUser();
            if (!ObjectUtils.isEmpty(userDto)) {
                MemberDto member = memberClient.getMember(userDto.getUserId());
                if (!ObjectUtils.isEmpty(member)) {
                    itemTagListToAppDtos.forEach(n->{
                        if(!ObjectUtils.isEmpty(n.getItemList())){
                            n.getItemList().forEach(m->{
                                    if(!ObjectUtils.isEmpty(m.getVipPrice())){
                                        m.setMallPrice(m.getVipPrice());
                                    }
                            });
                        }

                    });
                }
            }

        }
        return itemTagListToAppDtos;
    }
}
