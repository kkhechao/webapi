package com.zqkh.webapi.web.v1.healthy;

import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.domain.dto.test.paper.TestPaperTypeListDto;
import com.zqkh.webapi.context.service.TestPaperTypeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测题类型控制层
 *
 * @author 东来
 * @create 2018/6/7 0007
 */
@RestController
@RequestMapping("/healthy/test/paper/type")
public class TestPaperTypeController {


    @Resource
    private TestPaperTypeService testPaperTypeService;

    /**
     * 测题类型列表
     *
     * @param familyMemberId
     * @return
     */
    @GetMapping("")
    @ApiOperation(value = "api_healthy_test_type_list")
    @ApiImplicitParam(value = "家庭成员编号", name = "familyMemberId", paramType = "query", dataType = "String")
    @Anonymous
    List<TestPaperTypeListDto> list(@RequestParam(value = "familyMemberId", required = false) String familyMemberId) {
        return testPaperTypeService.list(familyMemberId);
    }

    ;


}
