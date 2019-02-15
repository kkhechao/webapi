package com.zqkh.webapi.web.v1.archive;

import com.zqkh.archive.feign.dto.BasicArchiveDto;
import com.zqkh.archive.feign.dto.FamilyMemberDto;
import com.zqkh.webapi.context.service.FamilyMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author hty
 * @create 2018-01-30 19:11
 **/
@RestController
@RequestMapping("/archive/familyMember")
public class FamilyMemberController {

    @Autowired
    FamilyMemberService familyMemberService;

    @ApiOperation(value = "api_familyMember_getFamilyMemberList")
    @GetMapping("/getFamilyMemberList")
    List<FamilyMemberDto> getFamilyMemberList(){
        return familyMemberService.getFamilyMemberList();
    }

    /**
     * 删除成员
     * @param memberId
     */
    @ApiOperation(value = "api_familyMember_removeFamilyMemberById")
    @GetMapping("/removeFamilyMemberById")
    void removeFamilyMemberById(@RequestParam("memberId") String memberId){
        familyMemberService.removeFamilyMemberById(memberId);
    }

    /**
     * 修改称呼
     * @param familyMemberDto
     */
    @ApiOperation(value = "api_familyMember_changeAppellation")
    @PostMapping("/changeAppellation")
    void changeAppellation(@RequestBody @Valid FamilyMemberDto familyMemberDto){
        familyMemberService.changeAppellation(familyMemberDto);
    }

    /**
     * 修改头像
     * @param familyMemberDto
     */
    @ApiOperation(value = "api_familyMember_changeAvatar")
    @PostMapping("/changeAvatar")
    void changeAvatar(@RequestBody @Valid FamilyMemberDto familyMemberDto){
        familyMemberService.changeAvatar(familyMemberDto);
    }

    /**
     * 修改姓名
     * @param BasicArchiveDto
     */
    @ApiOperation(value = "api_familyMember_changeName")
    @PostMapping("/changeName")
    public   BasicArchiveDto changeName(@RequestBody @Valid BasicArchiveDto BasicArchiveDto){
     return    familyMemberService.changeName(BasicArchiveDto);
    }

    /**
     * 修改性别
     * @param BasicArchiveDto
     */
    @ApiOperation(value = "api_familyMember_changeSex")
    @PostMapping("/changeSex")
    public  BasicArchiveDto changeSex(@RequestBody @Valid BasicArchiveDto BasicArchiveDto){
        return  familyMemberService.changeSex(BasicArchiveDto);
    }

    /**
     * 修改血型
     * @param BasicArchiveDto
     */
    @ApiOperation(value = "api_familyMember_updateBloodType")
    @PostMapping("/changeBloodType")
    public BasicArchiveDto changeBloodType(@RequestBody @Valid BasicArchiveDto BasicArchiveDto){
        return  familyMemberService.changeBlodType(BasicArchiveDto);
    }

    /**
     * 修改所在地
     * @param BasicArchiveDto
     */
    @ApiOperation(value = "api_familyMember_changeNativePlace")
    @PostMapping("/changeNativePlace")
    public BasicArchiveDto changeNativePlace(@RequestBody @Valid BasicArchiveDto BasicArchiveDto){
        return  familyMemberService.changeNativePlace(BasicArchiveDto);
    }

    /**
     * 修改名族
     * @param BasicArchiveDto
     */
    @ApiOperation(value = "api_familyMember_changeEthnic")
    @PostMapping("/changeEthnic")
    public BasicArchiveDto changeEthnic(@RequestBody @Valid BasicArchiveDto BasicArchiveDto){
        return familyMemberService.changeEthnic(BasicArchiveDto);
    }

    /**
     * 修改职业
     * @param BasicArchiveDto
     */
    @ApiOperation(value = "api_familyMember_changeProfession")
    @PostMapping("/changeProfession")
    public BasicArchiveDto changeProfession(@RequestBody @Valid BasicArchiveDto BasicArchiveDto){
        return  familyMemberService.changeProfession(BasicArchiveDto);
    }
    /**
     * 修改体征
     * @param BasicArchiveDto
     */
    @ApiOperation(value = "api_familyMember_changeBodyInfo")
    @PostMapping("/changeBodyInfo")
    public BasicArchiveDto changeBodyInfo(@RequestBody @Valid BasicArchiveDto BasicArchiveDto){
        return familyMemberService.changeBodyInfo(BasicArchiveDto);
    }

    /**
     * 修改基本档案
     * @param BasicArchiveDto
     */
    @ApiOperation(value = "api_familyMember_updateBasicArchive")
    @PostMapping("/updateBasicArchive")
    public BasicArchiveDto updateBasicArchive(@RequestBody @Valid BasicArchiveDto BasicArchiveDto){
        return familyMemberService.updateBasicArchive(BasicArchiveDto);
    }



    /**
     *添加基本档案
     * @param BasicArchiveDto
     */
    @ApiOperation(value = "api_familyMember_addBasicArchive")
    @PostMapping("/addBasicArchive")
   public BasicArchiveDto addBasicArchive(@RequestBody @Valid BasicArchiveDto BasicArchiveDto){
       return familyMemberService.addBasicArchive(BasicArchiveDto);
    }

    /**
     *成员档案详情
     */
    @ApiOperation(value = "api_familyMember_getFamilyMemberDetail")
    @GetMapping("/getFamilyMemberDetail")
    public BasicArchiveDto getFamilyMemberDetail(@RequestParam("memberId")String memberId){
        return familyMemberService.getFamilyMemberDetail(memberId);
    }
}
