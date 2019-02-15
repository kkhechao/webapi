package com.zqkh.webapi.context.utils;

import java.util.Random;

/**
 * @author wenjie
 * @date 2017/12/11 0011 10:05
 */
public class UtilTools {

    /**
     * 获得指定长度的短信验证码
     *
     * @param length
     * @return
     */
    public static synchronized String getCaptcha(int length) {
        String[] beforeShuffle = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(beforeShuffle[new Random().nextInt(10)]);
        }
        return sb.toString();
    }


}
