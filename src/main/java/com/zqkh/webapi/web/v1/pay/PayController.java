package com.zqkh.webapi.web.v1.pay;

import com.alipay.api.AlipayApiException;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.service.PayService;
import lombok.extern.log4j.Log4j;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author wenjie
 * @date 2018/1/9 0009 14:48
 */
@RequestMapping("/pay")
@RestController
@Log4j
public class PayController {

    @Autowired
    private PayService payService;

    /**
     * 付款成功后回调
     */
    @Anonymous
    @PostMapping("/wechat/call-back")
    public String wechatPayCallBack(HttpServletRequest request) throws JDOMException, IOException {
        return payService.wechatCallBack(request);
    }

    /**
     * 付款成功后回调
     */
    @Anonymous
    @PostMapping("/alipay/call-back")
    public String alipayCallBack(HttpServletRequest request)throws AlipayApiException{
        Map<String, String> params = new HashMap();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return payService.alipayCallBack(params);
    }

}
