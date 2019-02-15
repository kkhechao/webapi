package com.zqkh.webapi.context.service;

import com.zqkh.webapi.context.dto.ActionDto;

import java.util.List;

/**
 * @author hty
 * @create 2017-12-12 16:01
 **/

public interface SystemService {

    String getAreaCodeList();

    List<ActionDto> getActions(String swaggerGroup);
}
