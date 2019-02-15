package com.zqkh.webapi.web.v1.wemedia;

import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.service.FollowService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 关注WEB API Controller
 *
 * @author 悭梵
 * @date Created in 2018-05-30 14:13
 */
@RestController
@RequestMapping("/me/media/follow")
public class FollowController {

    @Resource
    private FollowService followService;

    /**
     * 关注专栏
     *
     * @param columnId
     */
    @PostMapping("")
    @ApiOperation(value = "api_follow_column", notes = "关注专栏")
    @ApiImplicitParam(name = "columnId", value = "专栏标识", paramType = "query")
    public void follow(@RequestBody String columnId) {
        followService.follow(columnId);
    }

    /**
     * 关注专栏
     *
     * @param columnId
     */
    @PostMapping("/unfollow")
    @ApiOperation(value = "api_unfollow_column", notes = "取消关注专栏")
    @ApiImplicitParam(name = "columnId", value = "专栏标识", paramType = "query")
    public void unfollow(@RequestBody String columnId) {
        followService.unfollow(columnId);
    }

    /**
     * 专栏关注数量，即粉丝数量
     *
     * @param columnId
     */
    @Anonymous
    @GetMapping("/count")
    @ApiOperation(value = "api_follow_count", notes = "专栏关注数量，即粉丝数量")
    @ApiImplicitParam(name = "columnId", value = "专栏标识", paramType = "query")
    public long followCount(@RequestParam(name = "columnId") String columnId) {
        return followService.followCount(columnId);
    }
}
