package com.zqkh.webapi.web.v1.wemedia;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.dto.wemedia.album.AlbumInfoDto;
import com.zqkh.webapi.context.dto.wemedia.album.AlbumListDto;
import com.zqkh.webapi.context.service.AlbumService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 专辑控制层
 *
 * @author 东来
 * @create 2018/4/6 0006
 */
@RestController
@RequestMapping("/me/media/album")
public class AlbumController {


    @Resource
    private AlbumService albumService;

    /**
     * 获取专辑列表
     *
     * @param typeId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("")
    @ApiImplicitParams({@ApiImplicitParam(name = "typeId", paramType = "query", value = "分类编号"),
            @ApiImplicitParam(name = "pageIndex", value = "第几页", paramType = "query", defaultValue = "1")
            , @ApiImplicitParam(name = "pageSize", value = "每页显示多少条", paramType = "query", defaultValue = "20")})
    @ApiOperation(value = "api_album_list", notes = "专辑列表")
    @Anonymous
    PageResult<AlbumListDto> getAlbumList(@RequestParam(name = "typeId", required = false) String typeId,
                                          @RequestParam(name = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        return albumService.getAlbumList(typeId, pageIndex, pageSize);
    }


    /**
     * 专辑详情
     *
     * @param id
     * @return
     */
    @Anonymous
    @GetMapping("/info")
    @ApiImplicitParam(name = "id", value = "专辑编号", paramType = "query")
    @ApiOperation(value = "api_album_info", notes = "专辑详情")
    AlbumInfoDto albumInfoDto(@RequestParam(name = "id", required = false) String id) {
        return albumService.getAlbumInfo(id);
    }

    /**
     * 精选专辑列表
     *
     * @param lastAlbumIds
     * @return
     */
    @Anonymous
    @GetMapping("/choice")
    @ApiOperation(value = "api_choice_album_list", notes = "精选专辑列表")
    @ApiImplicitParam(name = "lastAlbumIds", paramType = "query", value = "上一页专辑编号,多个用英文,分割")
    List<AlbumListDto> choiceAlbumList(@RequestParam(name = "lastAlbumIds", required = false) String lastAlbumIds) {
        return albumService.choiceAlbumList(lastAlbumIds);
    }

}
