package com.zqkh.webapi.context.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.ObjectUtils;


/**
 * Jsoup工具类
 *
 * @author 东来
 * @create 2018/4/15 0015
 */
public class JsoupUtil {


    /**
     *
     * @作者：严祥
     * @创建时间:2016年3月15日
     * @方法说明:html标签过滤
     * @param htmlStr
     * @param substring:截取多少位,0则不进行截取,为null则不进行内容转换
     * @return:String
     */
    public static String filterHtml(String htmlStr, Integer substring) {

        if (ObjectUtils.isEmpty(htmlStr)) {
            return htmlStr;
        }

        if (null == substring ) {
            return htmlStr;
        }

        Document doc = Jsoup.parse(htmlStr);
        String text = doc.text();

        // remove extra white space
        StringBuilder builder = new StringBuilder(text);
        int index = 0;
        while (builder.length() > index) {
            char tmp = builder.charAt(index);
            if (Character.isSpaceChar(tmp) || Character.isWhitespace(tmp)) {
                builder.setCharAt(index, ' ');
            }
            index++;
        }
        text = builder.toString().replaceAll(" +", " ").trim();

        if (ObjectUtils.isEmpty(text)) {
            return null;
        }

        if (substring == 0) {
            return text;
        }

        return text.substring(0,
                text.length() > substring ? substring : text.length());

    }
}
