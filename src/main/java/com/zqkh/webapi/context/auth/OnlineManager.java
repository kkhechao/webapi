package com.zqkh.webapi.context.auth;

import com.jovezhao.nest.cache.CacheContext;

/**
 * Created by zhaofujun on 2017/7/31.
 */
public class OnlineManager {
    private static final String cacheCode = "sessionState";

    public static boolean isOnline(String accountId) {

        CacheContext cacheContext = CacheContext.getContextByCode(cacheCode);
        return cacheContext.containsKey(accountId);
    }

    public static void setOnline(String accountId) {
        CacheContext cacheContext = CacheContext.getContextByCode(cacheCode);
        cacheContext.put(accountId, true);
    }
}
