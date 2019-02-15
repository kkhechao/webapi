package com.zqkh.webapi.context.dto.order;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeliverCollectorDto {

    private String address;
    private String geneOrder;
    private String startTime;
    private String endTime;

    private String remark;
}
