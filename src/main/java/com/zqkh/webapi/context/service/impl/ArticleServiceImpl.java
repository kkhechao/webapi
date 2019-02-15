package com.zqkh.webapi.context.service.impl;

import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.dto.wemedia.article.ArticleInfoDto;
import com.zqkh.webapi.context.dto.wemedia.article.AuthorDto;
import com.zqkh.webapi.context.dto.wemedia.article.MediaTypeEnumDto;
import com.zqkh.webapi.context.dto.wemedia.browse.BrowseTypeDto;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.ArticleService;
import com.zqkh.webapi.context.utils.ArticleUtil;
import com.zqkh.wemedia.feign.ArticleFeignClient;
import com.zqkh.wemedia.feign.BrowseFeignClient;
import com.zqkh.wemedia.feign.CommentFeignClient;
import com.zqkh.wemedia.feign.vo.BrowseVo;
import com.zqkh.wemedia.feign.vo.MediaTypeEnum;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 文章详情业务接口
 *
 * @author 东来
 * @create 2018/4/6 0006
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleFeignClient articleFeignClient;

    @Resource
    private BrowseFeignClient browseFeignClient;

    @Resource
    private CommentFeignClient commentFeignClient;

    @Override
    public ArticleInfoDto getArticleInfo(String id) {

        if (ObjectUtils.isEmpty(id)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "文章编号为空");
        }

        com.zqkh.wemedia.feign.dto.article.ArticleInfoDto articleInfoDto = articleFeignClient.readInfo(id);

        if (ObjectUtils.isEmpty(articleInfoDto)) {
            return null;
        }

        JWTUserDto userDto = AuthManager.currentUser();
        if (userDto != null) {
            /* 添加浏览历史 */
            BrowseVo browseVo = new BrowseVo();
            browseVo.setUserId(userDto.getUserId());
            browseVo.setObjectId(articleInfoDto.getId());
            browseVo.setBrowseType(BrowseTypeDto.ARTICLE.name());
            browseFeignClient.addBrowse(browseVo);
        }

        ArticleInfoDto result = new ArticleInfoDto();
        result.setId(articleInfoDto.getId());
        result.setContent(articleInfoDto.getContent());
        result.setIconUrl(articleInfoDto.getIconUrl());
        result.setKeyword(articleInfoDto.getKeyword());
        result.setMediaType(MediaTypeEnumDto.getMediaTypeEnumVo(articleInfoDto.getMediaType().name()));
        result.setContent(articleInfoDto.getContent());
        if (articleInfoDto.getMediaType().equals(MediaTypeEnum.TEXT)) {
            if (!ObjectUtils.isEmpty(articleInfoDto.getContent())) {
                result.setSynopsis(ArticleUtil.contentIntercept(articleInfoDto.getContent(), 100));
            }
        } else {
            result.setSynopsis(articleInfoDto.getSynopsis());
        }
        result.setTitle(articleInfoDto.getTitle());
        result.setMediaUrl(articleInfoDto.getMediaUrl());
        result.setViewCount(articleInfoDto.getViewCount() + articleInfoDto.getViewBase());
        result.setAlbumId(articleInfoDto.getAlbumId());
        AuthorDto authorDto = new AuthorDto();
        authorDto.setHeadUrl(articleInfoDto.getAuthor().getHeadUrl());
        authorDto.setNickName(articleInfoDto.getAuthor().getNickName());
        result.setAuthor(authorDto);
        result.setCreateTime(articleInfoDto.getCreateTime());
        result.setCommentCount(commentFeignClient.count(articleInfoDto.getId()));
        return result;
    }
}
