package com.zqkh.webapi.context.service;

import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.dto.wemedia.album.AlbumInfoDto;
import com.zqkh.webapi.context.dto.wemedia.album.AlbumListDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 专辑业务接口
 *
 * @author 东来
 * @create 2018/4/6 0006
 */
public interface AlbumService {

    /**
     * 获取专辑列表
     * @param typeId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<AlbumListDto> getAlbumList(String typeId, Integer pageIndex, Integer pageSize);


    /**
     * 专辑详情
     * @param id
     * @return
     */
    AlbumInfoDto getAlbumInfo(@PathVariable("id")String id);

    /**
     * 精选专辑列表
     * @param lastAlbumIds 上一页专辑编号,多个用英文,分割
     * @return
     */
    List<AlbumListDto> choiceAlbumList(@RequestParam(name = "lastAlbumIds", required = true) String lastAlbumIds);
}
