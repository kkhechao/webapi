package com.zqkh.webapi.web.v1.express;

import com.zqkh.express.feign.ExpressClient;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.service.ExpressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wenjie
 * @date 2018/2/1 0001 15:29
 */
@RestController
@RequestMapping("/express")
@Slf4j
public class ExpressController {

    @Autowired
    private ExpressService expressService;
    @Autowired
    private ExpressClient expressClient;

    @Anonymous
    @RequestMapping("/subscribe")
    public void test(HttpServletRequest request) {
        String dataSign = request.getParameter("DataSign");
        String requestType = request.getParameter("RequestType");
        String requestData = request.getParameter("RequestData");
        expressService.subscribeCallBack(dataSign, requestType, requestData);
    }


    /**
     * 顺丰callback
     *
     * @param context
     * @return
     */
    @Anonymous
    @RequestMapping(value = "/route/back", method = RequestMethod.POST)
    public String sfCallBack(@RequestBody String context) {
        log.info("顺丰回调推送：" + context);
        String subscribe = expressClient.subscribe(context);
        return subscribe;
    }

    @Anonymous
    @RequestMapping(value = "/state/push", method = RequestMethod.POST, produces = "application/xml")
    public String statePush(@RequestBody String context) {
        log.info("顺丰状态推送：" + context);
        String result = expressClient.push(context);
        return result;
    }
}
