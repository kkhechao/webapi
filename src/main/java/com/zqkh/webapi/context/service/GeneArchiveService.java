package com.zqkh.webapi.context.service;

import com.zqkh.webapi.context.dto.archive.gene.GeneArchiveInfoDto;

/**
 * 基因档案业务接口
 *
 * @author 东来
 * @create 2018/3/2 0002
 */
public interface GeneArchiveService {


    /**
     * 根据基因订单编号查询基因档案
     * @param geneOrderId:基因订单编号
     * @return
     */
    GeneArchiveInfoDto getGeneArchiveInfoByOrderId(String geneOrderId);


    /**
     * 获取基因档案示例报告
     *
     * @param sex:性别,man,woman
     * @return
     */
    GeneArchiveInfoDto getSampleReport(String sex);

}
