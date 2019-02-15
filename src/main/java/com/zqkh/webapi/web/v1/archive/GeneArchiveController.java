package com.zqkh.webapi.web.v1.archive;

import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.dto.archive.gene.GeneArchiveInfoDto;
import com.zqkh.webapi.context.service.GeneArchiveService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 基因档案控制层
 *
 * @author 东来
 * @create 2018/2/1 0001
 */
@RestController
@RequestMapping("/gene/archive")
public class GeneArchiveController  {


    @Resource
    private GeneArchiveService geneArchiveService;


    /**
     * 根据基因订单编号查询基因档案
     * @param geneOrderId:基因订单编号
     * @return
     */
    @Anonymous
    @ApiOperation(value = "api_gene_archive_info", notes = "基因档案报告详情")
    @ApiImplicitParam(name = "geneOrderId",required = true,value="基因订单编号",paramType="query",dataType="String")
    @GetMapping("/info")
    GeneArchiveInfoDto getGeneArchiveInfoByOrderId(@RequestParam("geneOrderId") String geneOrderId){
        return geneArchiveService.getGeneArchiveInfoByOrderId(geneOrderId);
    };


    /**
     * 查看基因示例报告
     *
     * @return
     */
    @Anonymous
    @ApiOperation(value = "api_gene_archive_sample", notes = "基因档案示例报告")
    @GetMapping("/sample")
    @ApiImplicitParam(name = "sex", value = "性别,man,woman", paramType = "query", dataType = "String")
    GeneArchiveInfoDto getSampleReport(@RequestParam(value = "sex", required = false) String sex) {
        return geneArchiveService.getSampleReport(sex);
    }



}
