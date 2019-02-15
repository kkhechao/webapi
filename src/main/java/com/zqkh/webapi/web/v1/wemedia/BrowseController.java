package com.zqkh.webapi.web.v1.wemedia;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.dto.wemedia.browse.BrowseDto;
import com.zqkh.webapi.context.service.BrowseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 浏览历史WebAPI控制器层
 *
 * @author 悭梵
 * @date Created in 2018-05-08 15:51
 */
@RestController
@RequestMapping("/me/media/browse")
public class BrowseController {

    @Resource
    private BrowseService browseService;

    /**
     * 新增浏览历史
     *
     * @param objectId
     */
    @PostMapping("add")
    @ApiOperation(value = "api_browse_add", notes = "新增浏览历史")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objectId", paramType = "query", value = "浏览对象标识", required = true),
            @ApiImplicitParam(name = "browseType", paramType = "query", value = "浏览对象类型， ARTICLE：文章，ALBUM：专辑，TYPE：分类", required = true)
    })
    public void addBrowse(@RequestParam(name = "browseType") String browseType, @RequestParam(name = "objectId") String objectId) {
        browseService.addBrowse(browseType, objectId);
    }

    /**
     * 最近的一条浏览记录
     *
     * @return
     */
    @GetMapping("/lately")
    @ApiOperation(value = "api_browse_lately_one", notes = "查询指定用户最近的一条浏览记录")
    public BrowseDto latelyBrowseByUserId() {
        return browseService.latelyBrowseByUserId();
    }

    /**
     * 查询指定用户浏览历史
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("")
    @ApiOperation(value = "api_browse_list", notes = "查询指定用户浏览历史")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示多少条", paramType = "query", defaultValue = "20")
    })
    public PageResult<BrowseDto> list(@RequestParam(name = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        return browseService.list(pageIndex, pageSize);
    }


}
