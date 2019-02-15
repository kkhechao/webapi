package com.zqkh.webapi.context.service.impl;

import com.zqkh.order.feign.ExpressClient;
import com.zqkh.order.feign.dto.SubscribeDto;
import com.zqkh.webapi.context.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wenjie
 * @date 2018/2/1 0001 17:26
 */
@Service
public class ExpressServiceImpl implements ExpressService {

    @Autowired
    private ExpressClient expressClient;

    @Override
    public void subscribeCallBack(String dataSign, String requestType, String requestData) {
        SubscribeDto subscribeDto = new SubscribeDto();
        subscribeDto.setDataSign(dataSign);
        subscribeDto.setRequestData(requestData);
        subscribeDto.setRequestType(requestType);
        expressClient.subscribe(subscribeDto);
    }
}
