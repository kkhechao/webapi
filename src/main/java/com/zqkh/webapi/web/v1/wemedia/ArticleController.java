package com.zqkh.webapi.web.v1.wemedia;

import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.dto.wemedia.article.ArticleInfoDto;
import com.zqkh.webapi.context.service.ArticleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 文章控制
 *
 * @author 东来
 * @create 2018/4/6 0006
 */
@RestController
@RequestMapping("/me/media/article")
public class ArticleController {


    @Resource
    private ArticleService articleService;


    /**
     * 获取文章详情
     * @param id:文章编号
     * @return
     */
    @Anonymous
    @GetMapping("/info")
    @ApiImplicitParam(name = "id",value = "文章编号",paramType = "query")
    @ApiOperation(value = "api_get_articleInfo", notes = "查询文章详情")
    ArticleInfoDto getArticleInfo(@RequestParam(name = "id",required = false) String id){
            return articleService.getArticleInfo(id);
    }


}
