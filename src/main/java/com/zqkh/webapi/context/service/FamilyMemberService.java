package com.zqkh.webapi.context.service;

import com.zqkh.archive.feign.dto.BasicArchiveDto;
import com.zqkh.archive.feign.dto.FamilyMemberDto;

import java.util.List;

/**
 * @author hty
 * @create 2018-01-30 17:05
 **/

public interface FamilyMemberService {
    List<FamilyMemberDto> getFamilyMemberList();

    void removeFamilyMemberById(String memberId);

    void changeAppellation(FamilyMemberDto familyMemberDto);

    void changeAvatar(FamilyMemberDto familyMemberDto);

    BasicArchiveDto changeName(BasicArchiveDto basicArchiveDto);

    BasicArchiveDto changeSex(BasicArchiveDto basicArchiveDto);

    BasicArchiveDto changeBlodType(BasicArchiveDto basicArchiveDto);

    BasicArchiveDto changeNativePlace(BasicArchiveDto basicArchiveDto);

    BasicArchiveDto changeEthnic(BasicArchiveDto basicArchiveDto);

    BasicArchiveDto changeProfession(BasicArchiveDto basicArchiveDto);

    BasicArchiveDto changeBodyInfo(BasicArchiveDto basicArchiveDto);

    BasicArchiveDto addBasicArchive(BasicArchiveDto basicArchiveDto);

    BasicArchiveDto getFamilyMemberDetail(String memberId);

    BasicArchiveDto updateBasicArchive(BasicArchiveDto basicArchiveDto);
}
