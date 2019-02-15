package com.zqkh.webapi.web.v1.wemedia;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.dto.wemedia.column.ColumnDto;
import com.zqkh.webapi.context.dto.wemedia.column.ColumnListDto;
import com.zqkh.webapi.context.service.ColumnService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 栏目管理WEB API
 *
 * @author 悭梵
 * @date Created in 2018-06-01 16:30
 */
@RestController
@RequestMapping("/me/media/column")
public class ColumnController {

    @Resource
    private ColumnService columnService;

    /**
     * 专栏信息
     *
     * @param id
     * @return
     */
    @Anonymous
    @GetMapping("/info")
    @ApiOperation(value = "api_column_info", notes = "指定专栏信息")
    @ApiImplicitParam(name = "id", value = "专栏标识", paramType = "query")
    public ColumnDto info(@RequestParam("id") String id) {
        return columnService.info(id);
    }

    /**
     * 专栏列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Anonymous
    @GetMapping("")
    @ApiOperation(value = "api_column_list", notes = "专栏列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示多少条", paramType = "query", defaultValue = "20")
    })
    public PageResult<ColumnListDto> list(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex, @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize) {
        return columnService.list(pageIndex, pageSize);
    }

}
