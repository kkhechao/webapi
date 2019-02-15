package com.zqkh.webapi.context.service.impl;

import com.jovezhao.nest.starter.AppService;
import com.zqkh.archive.feign.FamilyMemberClient;
import com.zqkh.archive.feign.dto.BasicArchiveDto;
import com.zqkh.archive.feign.dto.FamilyMemberDto;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.domain.dto.user.IdCardValidateDto;
import com.zqkh.webapi.context.domain.vo.user.IdCardValidateVo;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.FamilyMemberService;
import com.zqkh.webapi.context.service.RealNameAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author hty
 * @create 2018-01-30 17:06
 **/
@AppService
public class FamilyMemberServiceImpl implements FamilyMemberService {

    @Autowired
    FamilyMemberClient familyMemberClient;


    @Autowired
    RealNameAuthService realNameAuthService;


    @Override
    public List<FamilyMemberDto> getFamilyMemberList() {
        JWTUserDto userDto = AuthManager.currentUser();
        return familyMemberClient.getFamilyMemberList(userDto.getUserId());
    }

    @Override
    public void removeFamilyMemberById(String memberId) {
        familyMemberClient.removeFamilyMemberById(memberId);
    }

    @Override
    public void changeAppellation(FamilyMemberDto familyMemberDto) {
        familyMemberClient.changeAppellation(familyMemberDto);
    }

    @Override
    public void changeAvatar(FamilyMemberDto familyMemberDto) {
        familyMemberClient.changeAvatar(familyMemberDto);
    }

    @Override
    public BasicArchiveDto changeName(BasicArchiveDto basicArchiveDto) {
      return   familyMemberClient.updateBasicArchive(basicArchiveDto);
    }

    @Override
    public BasicArchiveDto changeSex(BasicArchiveDto basicArchiveDto) {
        return  familyMemberClient.updateBasicArchive(basicArchiveDto);
    }

    @Override
    public BasicArchiveDto changeBlodType(BasicArchiveDto basicArchiveDto) {
        return familyMemberClient.updateBasicArchive(basicArchiveDto);
    }

    @Override
    public BasicArchiveDto changeNativePlace(BasicArchiveDto basicArchiveDto) {
        return familyMemberClient.updateBasicArchive(basicArchiveDto);
    }

    @Override
    public BasicArchiveDto changeEthnic(BasicArchiveDto basicArchiveDto) {
        return   familyMemberClient.updateBasicArchive(basicArchiveDto);
    }

    @Override
    public BasicArchiveDto changeProfession(BasicArchiveDto basicArchiveDto) {
        BasicArchiveDto basicArchiveDto1 = familyMemberClient.updateBasicArchive(basicArchiveDto);
        return  basicArchiveDto1;

    }

    @Override
    public BasicArchiveDto changeBodyInfo(BasicArchiveDto basicArchiveDto) {
        return  familyMemberClient.updateBasicArchive(basicArchiveDto);
    }

    @Override
    public BasicArchiveDto addBasicArchive(BasicArchiveDto basicArchiveDto) {
        JWTUserDto userDto = AuthManager.currentUser();
        basicArchiveDto.setCreater(userDto.getUserId());
        return familyMemberClient.addBasciArchive(basicArchiveDto);
    }

    @Override
    public BasicArchiveDto getFamilyMemberDetail(String memberId) {
        return familyMemberClient.getFamilyMemberDetail(memberId);
    }

    @Override
    public BasicArchiveDto updateBasicArchive(BasicArchiveDto basicArchiveDto) {

        if (ObjectUtils.isEmpty(basicArchiveDto)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "参数为空");
        }
        if (ObjectUtils.isEmpty(basicArchiveDto.getIdCard())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "身份证为空");
        }
        if (ObjectUtils.isEmpty(basicArchiveDto.getName())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "姓名为空");
        }

        //校验身份证
        IdCardValidateDto idCardValidateDto = realNameAuthService.idCardValidate(IdCardValidateVo.builder().idCard(basicArchiveDto.getIdCard()).name(basicArchiveDto.getName()).build());
        if (idCardValidateDto.getValidate()) {
            return familyMemberClient.updateBasicArchive(basicArchiveDto);
        }
        throw new BusinessException(BusinessExceptionEnum.INVALID_PARAMS.getCode(), idCardValidateDto.getMsg());

    }
}
