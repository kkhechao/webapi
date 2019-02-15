package com.zqkh.webapi.web.v1;

import com.google.common.collect.Maps;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.dto.ActionDto;
import com.zqkh.webapi.context.service.SystemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenjie
 * @date 2017/12/14 0014 15:24
 */
@RestController
@RequestMapping("/apiDictionary")
public class ApiDictionary {

    public static final String ACTIONS = "actions";
    @Autowired
    private SystemService systemService;

    @Anonymous
    @GetMapping
    @ApiOperation(value = "api_dictionary")
    public Map getDictionary(@RequestParam(value = "group", required = false) String swaggerGroup) {
        HashMap<String, Object> resultMap = Maps.newHashMap();
        List<ActionDto> actionDtos = systemService.getActions(swaggerGroup);
        resultMap.put(ACTIONS, actionDtos);
        return resultMap;
    }
}
