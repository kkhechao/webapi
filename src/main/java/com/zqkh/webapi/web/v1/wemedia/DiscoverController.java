package com.zqkh.webapi.web.v1.wemedia;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.dto.wemedia.discover.HealthChoicenessDto;
import com.zqkh.webapi.context.service.DiscoverService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 发现控制层
 *
 * @author 东来
 * @create 2018/4/6 0006
 */
@RestController
@RequestMapping("/me/media/discover")
public class DiscoverController {

    @Resource
    private DiscoverService discoverService;

    /**
     * 健康精选
     *
     * @param typeId:分类编号
     * @param albumId:专辑编号
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Anonymous
    @ApiOperation(value = "api_discover_health_choiceness", notes = "健康精选")
    @GetMapping("/health/choiceness")
    @ApiImplicitParams({@ApiImplicitParam(name = "分类编号", value = "typeId", required = false, paramType = "query", dataType = "String")
            , @ApiImplicitParam(name = "专辑编号", value = "albumId", required = false, paramType = "query", dataType = "String")
            , @ApiImplicitParam(name = "第几页", value = "pageIndex", required = false, defaultValue = "1", paramType = "query", dataType = "String")
            , @ApiImplicitParam(name = "每页显示", value = "pageSize", required = false, defaultValue = "20", paramType = "query", dataType = "String")})
    PageResult<HealthChoicenessDto> healthChoiceness(@RequestParam(name = "typeId", required = false) String typeId,
                                                     @RequestParam(name = "albumId", required = false) String albumId,
                                                     @RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                                     @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return discoverService.healthChoiceness(typeId, albumId, pageIndex, pageSize);

    }


}
