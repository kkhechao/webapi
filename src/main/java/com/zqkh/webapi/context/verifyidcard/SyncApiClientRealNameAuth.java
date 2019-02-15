package com.zqkh.webapi.context.verifyidcard;

import com.alibaba.cloudapi.sdk.core.BaseApiClient;
import com.alibaba.cloudapi.sdk.core.BaseApiClientBuilder;
import com.alibaba.cloudapi.sdk.core.annotation.NotThreadSafe;
import com.alibaba.cloudapi.sdk.core.enums.Method;
import com.alibaba.cloudapi.sdk.core.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.core.enums.Scheme;
import com.alibaba.cloudapi.sdk.core.model.ApiRequest;
import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.cloudapi.sdk.core.model.BuilderParams;

public class SyncApiClientRealNameAuth extends BaseApiClient {

    public final static String GROUP_HOST = "idcard.market.alicloudapi.com";

    public SyncApiClientRealNameAuth(BuilderParams builderParams) {
        super(builderParams);
    }


    @NotThreadSafe
    public static class Builder extends BaseApiClientBuilder<Builder, SyncApiClientRealNameAuth> {

        @Override
        protected SyncApiClientRealNameAuth build(BuilderParams params) {
            return new SyncApiClientRealNameAuth(params);
        }
    }

    public static Builder newBuilder() {
        return new SyncApiClientRealNameAuth.Builder();
    }

    public static SyncApiClientRealNameAuth getInstance() {
        return getApiClassInstance(SyncApiClientRealNameAuth.class);
    }

    public ApiResponse validation(String cardno, String name) {
        String _apiPath = "/lianzhuo/idcard";

        ApiRequest _apiRequest = new ApiRequest(Scheme.HTTP, Method.GET, GROUP_HOST, _apiPath);

        _apiRequest.addParam("cardno", cardno, ParamPosition.QUERY, true);
        _apiRequest.addParam("name", name, ParamPosition.QUERY, true);

        return syncInvoke(_apiRequest);
    }
}
