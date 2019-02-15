package com.zqkh.webapi.context.service;

import com.zqkh.webapi.context.dto.wemedia.article.ArticleInfoDto;

/**
 * 文章业务接口
 *
 * @author 东来
 * @create 2018/4/6 0006
 */
public interface ArticleService {

    /**
     * 获取文章详情
     * @param id:文章编号
     * @return
     */
    ArticleInfoDto getArticleInfo(String id);
}
