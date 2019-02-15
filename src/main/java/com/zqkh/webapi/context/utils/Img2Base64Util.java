package com.zqkh.webapi.context.utils;

import sun.misc.BASE64Decoder;

import java.io.IOException;


public class Img2Base64Util {

    /**
     * base64解码成数组
     *
     * @param imgStr
     * @return
     */
    public static byte[] toByte(String imgStr) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();

        //Base64解码
        byte[] b = new byte[0];
        try {
            b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("图片解码失败");
        }
        return b;
    }
}
