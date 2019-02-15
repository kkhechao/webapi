package com.zqkh.webapi.context.configurtion;

import com.jovezhao.nest.amq.AMQChannelProvider;
import com.jovezhao.nest.amq.AMQProviderConfig;
import com.jovezhao.nest.ddd.event.ChannelProvider;
import com.jovezhao.nest.ddd.event.EventConfigItem;
import com.zqkh.file.eventdto.AppendFileUseRecordDto;
import com.zqkh.healthy.event.dto.HrrestDto;
import com.zqkh.healthy.event.dto.RespiratoryRateDto;
import com.zqkh.sms.event.dto.SMSVerifyCodeDTO;
import com.zqkh.webapi.context.configurtion.properties.CloudConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wenjie
 * @date 2017/12/4 0004 15:30
 */
@Configuration
public class MQConfiguration {

    @Autowired
    private CloudConfigProperties cloudConfigProperties;

    @Bean
    public AMQProviderConfig getAMQProvider() {
        AMQProviderConfig providerConfig = new AMQProviderConfig();
        providerConfig.setSecretId(cloudConfigProperties.getAmq().getSecretId());
        providerConfig.setSecretKey(cloudConfigProperties.getAmq().getSecretKey());
        providerConfig.setEndpoint(cloudConfigProperties.getAmq().getEndpoint());
        return providerConfig;
    }

    @Bean
    public AMQChannelProvider getAMQChannelProvider(AMQProviderConfig providerConfig) {
        AMQChannelProvider channelProvider = new AMQChannelProvider(providerConfig);
        return channelProvider;
    }

    /**
     * 短信确认event bean
     *
     * @return
     */
    @Bean
    public EventConfigItem getEventConfigItem(ChannelProvider channelProvider) {
        EventConfigItem eventConfigItem = new EventConfigItem();
        eventConfigItem.setChannelProvider(channelProvider);
        eventConfigItem.setEventName(SMSVerifyCodeDTO.VERIFY_CODE_EVENT_NAME);
        return eventConfigItem;
    }


    /**
     * 添加文件使用记录  event bean
     *
     * @return
     */
    @Bean
    public EventConfigItem getAppendFileConfigItem(ChannelProvider channelProvider) {
        EventConfigItem eventConfigItem = new EventConfigItem();
        eventConfigItem.setChannelProvider(channelProvider);
        eventConfigItem.setEventName(AppendFileUseRecordDto.APPEND);
        return eventConfigItem;
    }


    @Bean
    public EventConfigItem sendHrrestNotification(ChannelProvider provider) {
        EventConfigItem eventConfigItem = new EventConfigItem();
        eventConfigItem.setChannelProvider(provider);
        eventConfigItem.setEventName(HrrestDto.EVENT_NAME);
        return eventConfigItem;
    }

    @Bean
    public EventConfigItem sendRespiratoryRateNotification(ChannelProvider provider) {
        EventConfigItem eventConfigItem = new EventConfigItem();
        eventConfigItem.setChannelProvider(provider);
        eventConfigItem.setEventName(RespiratoryRateDto.EVENT_NAME);
        return eventConfigItem;
    }


}
