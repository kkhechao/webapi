package com.zqkh.webapi.context.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author wenjie
 * @date 2018/1/19 0019 11:05
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmDto {

    private List<ItemIdNumDto> confirmItemDtoList;

}
