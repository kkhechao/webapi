package com.zqkh.webapi.context.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.live.model.v20161101.DescribeLiveStreamsOnlineListRequest;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.jovezhao.nest.starter.AppService;
import com.zqkh.webapi.context.configurtion.properties.LiveProperties;
import com.zqkh.webapi.context.service.AliLiveService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hty
 * @create 2018-03-07 14:36
 **/
@Log4j
@AppService
public class AliLiveServiceImpl implements AliLiveService {

    @Autowired
    private LiveProperties liveProperties;

    @Override
    public String getLiveStatus() {
        try {
            IClientProfile profile = DefaultProfile.getProfile(liveProperties.getRegionId(), liveProperties.getAccessKeyId(), liveProperties.getSecret());
            DefaultAcsClient client = new DefaultAcsClient(profile);
            DescribeLiveStreamsOnlineListRequest request = new DescribeLiveStreamsOnlineListRequest();
            request.setDomainName(liveProperties.getDomainName());
            request.setAppName(liveProperties.getAppName());
            request.setActionName(liveProperties.getActionName());
            request.setAcceptFormat(FormatType.JSON);
            HttpResponse httpResponse = client.doAction(request);
            return new String(httpResponse.getHttpContent());
        } catch (ServerException e) {
            log.error("直播状态获取失败：" + e.getMessage());
        } catch (ClientException e) {
            log.error("直播状态获取失败：" + e.getMessage());
        }
        return null;
    }


}
