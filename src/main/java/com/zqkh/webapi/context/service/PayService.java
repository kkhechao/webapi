package com.zqkh.webapi.context.service;

import com.alipay.api.AlipayApiException;
import org.jdom.JDOMException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author wenjie
 * @date 2018/1/9 0009 17:46
 */
public interface PayService {

    Map wechatPay(String ip, BigDecimal money,String billNumber);

    String wechatCallBack(HttpServletRequest request) throws JDOMException, IOException;

    Map wechatQuery(String transactionId) throws JDOMException, IOException;

    Map alipay(BigDecimal rechargeAmount, String billId);

    String alipayCallBack(Map<String, String> request) throws AlipayApiException;

    Map wechatH5Pay(String ip, BigDecimal money, String billNumber);
}
