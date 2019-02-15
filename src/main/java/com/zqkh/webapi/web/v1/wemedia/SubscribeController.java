package com.zqkh.webapi.web.v1.wemedia;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.dto.wemedia.subscribe.AlbumDto;
import com.zqkh.webapi.context.dto.wemedia.subscribe.SubscribeDto;
import com.zqkh.webapi.context.service.SubscribeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订阅WebAPI控制器实现
 *
 * @author 悭梵
 * @date Created in 2018-05-08 16:35
 */
@RestController
@RequestMapping("/me/media/subscribe")
public class SubscribeController {

    @Resource
    private SubscribeService subscribeService;

    /**
     * 订阅专辑
     *
     * @param albumDto
     */
    @PostMapping("add")
    @ApiOperation(value = "api_subscribe_add", notes = "新增订阅专辑")
    public void subscribe(@RequestBody AlbumDto albumDto) {
        subscribeService.subscribe(albumDto.getAlbumId());
    }

    /**
     * 取消订阅专辑
     *
     * @param albumDto
     */
    @PostMapping("delete")
    @ApiOperation(value = "api_subscribe_delete", notes = "取消订阅专辑")
    public void unsubscribe(@RequestBody AlbumDto albumDto) {
        subscribeService.unsubscribe(albumDto.getAlbumId());
    }

    /**
     * 查询指定用户订阅列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("")
    @ApiOperation(value = "api_subscribe_list", notes = "查询指定用户订阅列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示多少条", paramType = "query", defaultValue = "20")
    })
    public PageResult<SubscribeDto> list(@RequestParam(name = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                         @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        return subscribeService.list(pageIndex, pageSize);
    }
}
