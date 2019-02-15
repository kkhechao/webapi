package com.zqkh.webapi.web.v1.wemedia;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.dto.wemedia.type.TypeDto;
import com.zqkh.webapi.context.dto.wemedia.type.TypeListDto;
import com.zqkh.webapi.context.service.TypeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 分类 Web Api
 *
 * @author 悭梵
 * @date Created in 2018-04-06 17:25
 */
@RestController
@RequestMapping("/me/media/type")
public class TypeController {

    @Resource
    private TypeService typeService;

    /**
     * 分类详情
     *
     * @param id 分类编号
     * @return
     */
    @Anonymous
    @GetMapping("/info")
    @ApiImplicitParam(name = "id", value = "分类编号")
    @ApiOperation(value = "api_get_type_info", notes = "分类详情")
    public TypeDto getTypeInfo(@RequestParam(name = "id") String id) {
        return typeService.getTypeInfo(id);
    }

    /**
     * 分类详情
     *
     * @param pageIndex 第几页
     * @param pageSize  每页显示多少条
     * @return
     */
    @Anonymous
    @GetMapping("list")
    @ApiOperation(value = "api_page_list_type", notes = "分页查询分类列表", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "第几页", name = "pageIndex", paramType = "query", dataType = "int", required = false, defaultValue = "1"),
            @ApiImplicitParam(value = "每页显示多少条", name = "pageSize", paramType = "query", dataType = "int", required = false, defaultValue = "20")
    })
    public PageResult<TypeListDto> typeListPage(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                                @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return typeService.typeListPage(pageIndex, pageSize);
    }
}
