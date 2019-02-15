package com.zqkh.webapi.context.dto.order;

import com.zqkh.order.feign.dto.AddressInfoDto;
import com.zqkh.order.feign.dto.bsystem.ExpressInfoDto;
import com.zqkh.order.feign.dto.express.TrackDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author wenjie
 * @date 2018/1/23 0023 10:32
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpressDetailDto {

    private ExpressInfoDto expressInfoDto;

    private List<TrackDto> trackDtoList;

    private AddressInfoDto addressInfoDto;
}
