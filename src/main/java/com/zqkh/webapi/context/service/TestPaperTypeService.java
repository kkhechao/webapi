package com.zqkh.webapi.context.service;

import com.zqkh.webapi.context.domain.dto.test.paper.TestPaperTypeListDto;

import java.util.List;

/**
 * 测题类型业务接口
 *
 * @author 东来
 * @create 2018/6/7 0007
 */
public interface TestPaperTypeService {

    /**
     * 获取测题类型雷彪
     *
     * @param familyMemberId:家庭成员编号
     * @return
     */
    List<TestPaperTypeListDto> list(String familyMemberId);
}
