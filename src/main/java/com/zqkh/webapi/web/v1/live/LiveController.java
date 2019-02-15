package com.zqkh.webapi.web.v1.live;

import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.service.AliLiveService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/live")
public class LiveController {

    @Autowired
    private AliLiveService aliLiveService;

    @Anonymous
    @GetMapping("/getLiveStatus")
    @ApiOperation(value = "api_live_getLiveStatus")
    public String getCaptcha() {
        return aliLiveService.getLiveStatus();
    }

}
