package com.zqkh.webapi.context.dto.order;

import com.zqkh.item.feign.dto.ItemInfoInwardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wenjie
 * @date 2018/1/18 0018 18:21
 */
@Getter
@Setter
@AllArgsConstructor
public final class ItemNumDto {

    private ItemInfoInwardDto itemDto;

    private Integer num;

}
