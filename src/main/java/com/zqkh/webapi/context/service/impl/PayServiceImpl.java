package com.zqkh.webapi.context.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.google.common.collect.Maps;
import com.jovezhao.nest.utils.JsonUtils;
import com.zqkh.wallet.feign.BillClient;
import com.zqkh.webapi.context.configurtion.properties.PayProperties;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.service.PayService;
import com.zqkh.webapi.context.utils.wechat.HttpUtil;
import com.zqkh.webapi.context.utils.wechat.PayCommonUtil;
import com.zqkh.webapi.context.utils.wechat.WeChatRequestHander;
import com.zqkh.webapi.context.utils.wechat.XMLUtil;
import lombok.extern.slf4j.Slf4j;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weixin.popular.api.PayMchAPI;
import weixin.popular.bean.paymch.Unifiedorder;
import weixin.popular.bean.paymch.UnifiedorderResult;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author wenjie
 * @date 2018/1/9 0009 17:46
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    public static final String UTF_8 = "UTF-8";
    public static final String ORDER_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

    @Autowired
    private PayProperties payProperties;

    @Autowired
    BillClient billClient;

    @Override
    public Map wechatPay(String ip, BigDecimal money, String billNumber) {
        String appId = payProperties.getWechat().getAppId();
        String mchId = payProperties.getWechat().getMchId();
        String key = payProperties.getWechat().getKey();

        String currTime = PayCommonUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayCommonUtil.buildRandom(4) + "";
        String nonce_str = strTime + strRandom;

        Map map;
        try {
            map = getWeChatMap(ip, nonce_str,money, billNumber);
        } catch (JDOMException e) {
            throw new BusinessException(BusinessExceptionEnum.JDOM_PARSE_EXCEPTION.getCode(), BusinessExceptionEnum.JDOM_PARSE_EXCEPTION.getMessage());
        } catch (IOException e) {
            throw new BusinessException(BusinessExceptionEnum.IO_EXCEPTION.getCode(), BusinessExceptionEnum.IO_EXCEPTION.getMessage());
        }

        if (map == null || !"SUCCESS".equals(map.get("result_code"))) {
            throw new BusinessException(BusinessExceptionEnum.GET_PREPAY_INFO_FAIL.getCode(), BusinessExceptionEnum.GET_PREPAY_INFO_FAIL.getMessage());
        }
        SortedMap<Object, Object> resultMap = new TreeMap<>();
        //参数列表
        resultMap.put("appid", appId);
        resultMap.put("partnerid", mchId);
        resultMap.put("prepayid", map.get("prepay_id"));
        resultMap.put("noncestr", map.get("nonce_str"));
        resultMap.put("timestamp", PayCommonUtil.getTimeStamp());
        resultMap.put("package", "Sign=WXPay");

        //获得签名
        String sign = PayCommonUtil.createSign("UTF-8", resultMap, key);
        resultMap.put("sign", sign);

        return resultMap;

    }


    /**
     * 得到微信返回的xml
     *
     * @param nonce_str
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    private Map getWeChatMap(String ip, String nonce_str,BigDecimal money, String billNumber) throws JDOMException, IOException {

        String appId = payProperties.getWechat().getAppId();
        String mchId = payProperties.getWechat().getMchId();
        String key = payProperties.getWechat().getKey();
        String wxNotifyUrl = payProperties.getWechat().getNotifyUrl();
        String requestUrl = payProperties.getWechat().getRequestUrl();

        // 价格   注意：价格的单位是分
        //不能要小数点！！！！！ 取整数
        //FIXME 价格固定1分
        String order_price = String.valueOf(money.multiply(new BigDecimal("100")).intValue());

        // 获取发起电脑 ip

        // 回调接口
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        packageParams.put("appid", appId);
        packageParams.put("mch_id", mchId);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", "中清健康管家");
        packageParams.put("out_trade_no", billNumber);
        packageParams.put("total_fee", order_price);
        packageParams.put("spbill_create_ip", ip);
        packageParams.put("notify_url", wxNotifyUrl);
        packageParams.put("trade_type", "APP");
        //packageParams.put("attach", billNumber);


        String sign = PayCommonUtil.createSign(UTF_8, packageParams, key);
        packageParams.put("sign", sign);

        String requestXML = PayCommonUtil.getRequestXml(packageParams);

        String resXml = HttpUtil.postData(requestUrl, requestXML);

        return XMLUtil.doXMLParse(resXml);
    }

    @Override
    public String wechatCallBack(HttpServletRequest request) {
        String resXml = "";

        //处理回掉参数
        SortedMap packageParams = null;
        try {
            packageParams = WeChatRequestHander.dealWithRequest(request);
        } catch (JDOMException e) {
            throw new BusinessException(BusinessExceptionEnum.JDOM_PARSE_EXCEPTION.getCode(), BusinessExceptionEnum.JDOM_PARSE_EXCEPTION.getMessage());
        } catch (IOException e) {
            throw new BusinessException(BusinessExceptionEnum.IO_EXCEPTION.getCode(), BusinessExceptionEnum.IO_EXCEPTION.getMessage());
        }

        // 账号信息
        log.debug(packageParams.toString());
        //判断签名是否正确
        if (PayCommonUtil.isTenpaySign(UTF_8, packageParams, payProperties.getWechat().getKey())) {
            //------------------------------
            //处理业务开始
            //------------------------------
            if ("SUCCESS".equals(packageParams.get("result_code"))) {
                // 这里是支付成功
                //////////执行自己的业务逻辑////////////////
                //String mch_id = (String) packageParams.get("mch_id");
                //String openid = (String) packageParams.get("openid");
                //String is_subscribe = (String) packageParams.get("is_subscribe");
                String billNumber = (String) packageParams.get("out_trade_no");
                String transaction_id = (String) packageParams.get("transaction_id");
                //String billNumber = (String) packageParams.get("attach");

                //String total_fee = (String) packageParams.get("total_fee");

                //////////执行自己的业务逻辑////////////////
                billClient.processRechargeBillSuccessCallBack(billNumber, JSONUtils.toJSONString(packageParams));

                log.info("【微信方式】支付成功");
                //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
//                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
//                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                resXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";

            } else {
                log.info("支付失败,错误信息：" + packageParams.get("err_code"));
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }
            //------------------------------
            //处理业务完毕
            //------------------------------
        }
        return resXml;
    }

    @Override
    public Map wechatQuery(String transactionId) throws JDOMException, IOException {
        String appId = payProperties.getWechat().getAppId();
        String mchId = payProperties.getWechat().getMchId();
        String key = payProperties.getWechat().getKey();

        String currTime = PayCommonUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayCommonUtil.buildRandom(4) + "";
        String nonce_str = strTime + strRandom;


        // 回调接口
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        packageParams.put("appid", appId);
        packageParams.put("mch_id", mchId);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("transaction_id", transactionId);

        String sign = PayCommonUtil.createSign(UTF_8, packageParams, key);
        packageParams.put("sign", sign);

        String requestXML = PayCommonUtil.getRequestXml(packageParams);

        String resXml = HttpUtil.postData(ORDER_QUERY_URL, requestXML);

        return XMLUtil.doXMLParse(resXml);
    }


    @Override
    public Map alipay(BigDecimal rechargeAmount, String billId) {
        String privateKey = payProperties.getAlipay().getPrivateKey();
        String publicKey = payProperties.getAlipay().getPublicKey();
        String serverUrl = payProperties.getAlipay().getGatewayUrl();
        String appId = payProperties.getAlipay().getAppId();
        String notifyUrl = payProperties.getAlipay().getNotifyUrl();
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, "json", "UTF-8", publicKey,"RSA2");
        //创建API对应的request
        AlipayTradeAppPayRequest alipayRequest = new AlipayTradeAppPayRequest();
        //alipayRequest.setReturnUrl("http://shop.zhuangso.cn/pay/alipay/return");
        //在公共参数中设置回跳和通知地址

        alipayRequest.setNotifyUrl(notifyUrl);
        Map<String, Object> payParam = Maps.newHashMap();
        payParam.put("out_trade_no", billId);
        payParam.put("total_amount", String.valueOf(rechargeAmount.setScale(2, RoundingMode.HALF_EVEN)));
        payParam.put("subject", "中清健康管家");
        payParam.put("product_code", "QUICK_MSECURITY_PAY");
        //填充业务参数
        alipayRequest.setBizContent(JSON.toJSONString(payParam));
        AlipayTradeAppPayResponse alipayTradeAppPayResponse = null;
        try {
            alipayTradeAppPayResponse = alipayClient.sdkExecute(alipayRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new BusinessException(BusinessExceptionEnum.ALIPAY_EXCEPTION.getCode(), JsonUtils.toJsonString(payParam));
        }

        //调用SDK生成表单
        String sign =  alipayTradeAppPayResponse.getBody();
//        try {
//            sign =  URLEncoder.encode(sign, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("sign",sign);
        return  resultMap;
    }

    @Override
    public String alipayCallBack(Map<String, String> params) throws AlipayApiException {
        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, payProperties.getAlipay().getPublicKey(), "utf-8","RSA2");

        if (signVerified) {
            //验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            String trade_status = params.get("trade_status");
            if ("TRADE_FINISHED".equals(trade_status) || "TRADE_SUCCESS".equals(trade_status)) {
                //String tradeNo = params.get("trade_no");
                String outTradeNo = params.get("out_trade_no");
                // 业务处理
                billClient.processRechargeBillSuccessCallBack(outTradeNo, JSONUtils.toJSONString(params));
                log.info("支付宝回调成功,充值账单编号："+outTradeNo);
                return "success";
            } else {
                return "failure";
            }
        } else {
            //验签失败则记录异常日志，并在response中返回failure.
            log.error("====支付宝回调验证失败====");
            return "failure";
        }
    }

    @Override
    public Map wechatH5Pay(String ip, BigDecimal money, String billNumber) {
        String appId = payProperties.getWechat().getAppId();
        String mchId = payProperties.getWechat().getMchId();
        String key = payProperties.getWechat().getKey();

        Unifiedorder unifiedorder = new Unifiedorder();
        unifiedorder.setAppid(appId);
        unifiedorder.setMch_id(mchId);
        unifiedorder.setNonce_str(UUID.randomUUID().toString().replace("-", ""));

        String orderPrice = String.valueOf(money.multiply(new BigDecimal("100")).intValue());

        unifiedorder.setBody("会员升级");
        unifiedorder.setOut_trade_no(billNumber);
        unifiedorder.setTotal_fee(orderPrice);//单位分
        unifiedorder.setSpbill_create_ip(ip);//IP
        unifiedorder.setNotify_url(payProperties.getWechat().getNotifyUrl());
        unifiedorder.setTrade_type("MWEB");//JSAPI，NATIVE，APP，MWEB
        UnifiedorderResult unifiedorderResult = PayMchAPI.payUnifiedorder(unifiedorder, key);

        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("prepayid", unifiedorderResult.getPrepay_id());
        resultMap.put("mWeb_url", unifiedorderResult.getMweb_url());
        return resultMap;
    }

}
