package com.zqkh.webapi.context.utils.wechat;

import org.jdom.JDOMException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by wenjie on 2016/6/28.
 */
public final class WeChatRequestHander {

    private WeChatRequestHander() {
    }

    public static SortedMap dealWithRequest(HttpServletRequest request) throws JDOMException, IOException {
        //读取参数
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        inputStream = request.getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();

        //解析xml成map
        Map<String, String> m = new HashMap<>();
        m = XMLUtil.doXMLParse(sb.toString());

        String resXml = "";

        //过滤空 设置 TreeMap
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            String parameter = (String) it.next();
            String parameterValue = m.get(parameter);

            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }

        return packageParams;
    }
}
