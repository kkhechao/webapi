package com.zqkh.webapi.context.utils;

import org.springframework.util.ObjectUtils;

/**
 * 文章工具
 *
 * @author 东来
 * @create 2018/4/18 0018
 */
public class ArticleUtil {

    /**
     * 文章内容截取
     * @param html
     * @param num
     * @return
     */
    public static String contentIntercept(String html,int num) {
        String filetrHtml = JsoupUtil.filterHtml(html, num);
        String noFilter = JsoupUtil.filterHtml(html, 0);
        if (!ObjectUtils.isEmpty(filetrHtml)) {
            filetrHtml = filetrHtml.replace("“", "").replaceAll("”", "");
            if (noFilter.length() > num) {
                filetrHtml += filetrHtml + "......";
            }
        }
        return filetrHtml;
    }
}
