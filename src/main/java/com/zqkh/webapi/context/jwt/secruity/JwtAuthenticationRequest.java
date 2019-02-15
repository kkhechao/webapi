package com.zqkh.webapi.context.jwt.secruity;

import java.io.Serializable;

public class  JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String mobile;
    private String code;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String mobile, String code) {
        this.mobile = mobile;
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
