package com.zqkh.webapi.context.dto.order;

import com.zqkh.express.feign.dto.TrackDto;
import com.zqkh.gene.feign.dto.AddressInfoDto;
import com.zqkh.gene.feign.dto.bsystem.ExpressInfoDto;
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
public class GeneExpressDetailDto {

    private ExpressInfoDto expressInfoDto;

    private List<TrackDto> trackDtoList;

    private AddressInfoDto addressInfoDto;
}
