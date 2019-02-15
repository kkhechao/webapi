package com.zqkh.webapi.context.constant;


/**
 * Created by wenjie on 2017/12/15.
 */
public enum BusinessExceptionEnum {


    /**
     * 业务异常
     */
    INVALID_PARAMS(10000, "参数校验失败"),
    CAPTCHA_NOT_TRUE(100001, "验证码校验失败"),
    LOGIN_FAIL(100002, "登录失败"),
    IDCARD_VALID_FAIL(100003, "实名认证失败"),
    SWAGGER_NOT_FOUND(100004, "swagger未找到"),
    USER_NOT_AUTH(100005, "未实名认证"),
    BINDCARD_NAME_NOT_TRUE(100006, "银行卡持有人姓名与实名认证姓名不同"),
    WECHAT_LOGIN_FAIL(100007, "微信登录失败，用户未绑定手机号"),
    WECHAT_BINDING_FAIL(100008, "微信绑定失败，该微信号已经被绑定"),
    GET_PREPAY_INFO_FAIL(100009, "获取预支付订单信息失败"),
    JDOM_PARSE_EXCEPTION(100010, "JDOM转换异常"),
    IO_EXCEPTION(100011, "IO异常"),
    NO_ITEM_EXCEPTION(100012, "没有选择商品"),
    NO_ORDER_EXCEPTION(100013, "没有找到订单"),
    NO_AGENT_EXCEPTION(100014, "当前身份不是代理"),
    ERRRO_COLECTORNO_EXCEPTION(100015, "采集器编号错误"),
    NO_GENE_ORDER_EXCEPTION(100016, "基因订单不存在"),
    PARAM_IS_NULL(100017,"参数为空"),
    RESULT_IS_NULL(100018,"结果集为空"),
    RESULT_IS_FAILE(100019,"结果集错误"),
    ALIPAY_EXCEPTION(100020,"支付宝支付错误"),
    USER_NOT_ACTIVE(100021, "用户未激活"),
    USER_NOT_CROWN(100023, "非皇冠用户不能开店"),
    USER_ALREADY_VIP(100022, "您已经是VIP会员了"),
    /**
     * 广告异常
     */
     AD_BANNER_CODE_IS_NULL(100012,"广告获取失败"),

    /**
     * 广告未编码错误
     */
    AD_BANNER_CODE_ERR(100014,"广告获取失败"),

    /**
     * 基因订单异常
     */
    FAMILYID_IS_NULL(100015,"请选择一个成员"),

    FEIGN_EXCEPTION(200000, "feign异常"),
    SYSTEM_SETTING_FAAL(300000, "系统设置验证为通过");

    private int code;
    private String message;

    // 构造方法
    BusinessExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
