package com.zqkh.webapi.web.v1.wemedia;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.service.ListenHealthService;
import com.zqkh.wemedia.feign.dto.ListenHealthDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 听健康
 *
 * @author 悭梵
 * @date Created in 2018-07-11 16:47
 */
@RestController
@RequestMapping("/me/media/listen/health")
public class ListenHealthController {

    @Resource
    private ListenHealthService listenHealthService;

    @Anonymous
    @GetMapping("")
    @ApiOperation(value = "api_listen_health_list", notes = "听健康音乐列表")
    public PageResult<ListenHealthDto> list(@RequestParam(name = "typeId", required = false) String typeId,
                                            @RequestParam(name = "albumId", required = false) String albumId,
                                            @RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                            @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return listenHealthService.list(typeId, albumId, pageIndex, pageSize);
    }

    @Anonymous
    @PostMapping("{id}")
    @ApiOperation(value = "api_listen_health_read", notes = "听健康音乐记录")
    public void listenHealth(@PathVariable("id") String id) {
        listenHealthService.listenHealth(id);
    }
}
