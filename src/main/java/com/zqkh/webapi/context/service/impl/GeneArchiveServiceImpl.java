package com.zqkh.webapi.context.service.impl;

import com.zqkh.archive.feign.GeneArchiveClient;
import com.zqkh.archive.feign.dto.gene.DetectItemDto;
import com.zqkh.archive.feign.dto.gene.DetectionOfOverviewDto;
import com.zqkh.archive.feign.dto.gene.MorbidityAgeGroupDto;
import com.zqkh.file.dto.ResourceInfoDto;
import com.zqkh.file.feign.FileFeign;
import com.zqkh.webapi.context.configurtion.SampleReportConfiguration;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.dto.archive.disease.EpidemicAreaDto;
import com.zqkh.webapi.context.dto.archive.disease.ExaminationDto;
import com.zqkh.webapi.context.dto.archive.disease.HealthAdviceDto;
import com.zqkh.webapi.context.dto.archive.gene.DiseaseInfoDto;
import com.zqkh.webapi.context.dto.archive.gene.GeneArchiveInfoDto;
import com.zqkh.webapi.context.dto.archive.gene.LocusInfoDto;
import com.zqkh.webapi.context.dto.archive.gene.SampleInfoDto;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.service.GeneArchiveService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 基因档案业务实现
 *
 * @author 东来
 * @create 2018/3/2 0002
 */
@Service
public class GeneArchiveServiceImpl implements GeneArchiveService {

    @Resource
    private GeneArchiveClient geneArchiveClient;

    @Resource
    private SampleReportConfiguration sampleReportConfiguration;


    @Resource
    private FileFeign fileFeign;

    @Override
    public GeneArchiveInfoDto getGeneArchiveInfoByOrderId(String geneOrderId) {
        if(ObjectUtils.isEmpty(geneOrderId)){
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(),"请选择一个基因订单");
        }

        com.zqkh.archive.feign.dto.gene.GeneArchiveInfoDto geneArchiveInfoDto=geneArchiveClient.getGeneArchiveInfoByOrderId(geneOrderId);

        if(ObjectUtils.isEmpty(geneArchiveInfoDto)){
            throw new BusinessException(BusinessExceptionEnum.RESULT_IS_NULL.getCode(),"基因档案不存在");
        }

        GeneArchiveInfoDto geneArchiveInfo=new GeneArchiveInfoDto();
        geneArchiveInfo.setPackageName(geneArchiveInfoDto.getPackageName());
        SampleInfoDto sampleInfoDto=new SampleInfoDto();
        sampleInfoDto.setSampleType(geneArchiveInfoDto.getSampleType());
        sampleInfoDto.setAuditTime(geneArchiveInfoDto.getAuditTime());
        geneArchiveInfo.setSample(sampleInfoDto);
        geneArchiveInfo.setSuggest(geneArchiveInfoDto.getSuggest());
        geneArchiveInfo.setNickName(geneArchiveInfoDto.getNickName());
        geneArchiveInfo.setHeadUrl(geneArchiveInfoDto.getHeadUrl());
        List<DetectItemDto> detectItemDtos= geneArchiveInfoDto.getDetectItem();

        if(ObjectUtils.isEmpty(detectItemDtos)){
            throw new BusinessException(BusinessExceptionEnum.RESULT_IS_FAILE.getCode(),"检测尚未完成");
        }


        /**
         * 处理图片信息
         */
        List<String> resourceId = new ArrayList<>();

        if (!ObjectUtils.isEmpty(geneArchiveInfoDto.getDetectItem())) {
            geneArchiveInfoDto.getDetectItem().forEach(n -> {
                if (!ObjectUtils.isEmpty(n.getIcon())) {
                    resourceId.add(n.getIcon());
                }

                if (!ObjectUtils.isEmpty(n.getExamination())) {
                    n.getExamination().forEach(m -> {
                        if (!ObjectUtils.isEmpty(m.getIcon())) {
                            resourceId.add(n.getIcon());
                        }
                    });
                }

                if (!ObjectUtils.isEmpty(n.getHealthAdvice())) {
                    n.getHealthAdvice().forEach(m -> {
                        if (!ObjectUtils.isEmpty(m.getIcon())) {
                            resourceId.add(m.getIcon());
                        }
                    });
                }

                if (!ObjectUtils.isEmpty(n.getLocusInfo())) {
                    n.getLocusInfo().forEach(m -> {
                        if (!ObjectUtils.isEmpty(m.getLocusImageId())) {
                            resourceId.add(m.getLocusImageId());
                        }
                    });
                }
            });
        }

        List<ResourceInfoDto> resourceInfo = fileFeign.getResourceInfo(resourceId.stream().distinct().collect(Collectors.toList()));

        Map<String, String> resourceMap = new HashMap<>();
        if (!ObjectUtils.isEmpty(resourceInfo)) {
            resourceMap = resourceInfo.stream().collect(Collectors.toMap(ResourceInfoDto::getId, ResourceInfoDto::getUrl));
        }
        Map<String, String> urlMap = resourceMap;



        List<DiseaseInfoDto> disease=new ArrayList<>();


        detectItemDtos.forEach(n->{
            DiseaseInfoDto diseaseInfoDto=new DiseaseInfoDto();
            diseaseInfoDto.setEnglishName(n.getEnglishName());
            diseaseInfoDto.setName(n.getName());
            diseaseInfoDto.setRisk(DiseaseInfoDto.Risk.getRisk(n.getRisk().name()));

            diseaseInfoDto.setDesc(n.getDesc());
            diseaseInfoDto.setIcoUrl(!ObjectUtils.isEmpty(n.getIcon()) ? urlMap.get(n.getIcon()) : null);
            diseaseInfoDto.setSymptom(n.getSymptom());

            diseaseInfoDto.setReference(n.getReference());


            /**
             * 处理 涉及相关检查
             */
            if (!ObjectUtils.isEmpty(n.getExamination())) {
                List<ExaminationDto> examination = n.getExamination().stream().map(m -> {
                    ExaminationDto examinationDto = ExaminationDto.builder()
                            .title(m.getTitle())
                            .content(m.getContent())
                            .icon(m.getIcon())
                            .iconUrl(!ObjectUtils.isEmpty(m.getIcon()) ? urlMap.get(m.getIcon()) : null)
                            .build();
                    return examinationDto;
                }).collect(Collectors.toList());
                diseaseInfoDto.setExamination(examination);
            }

            /**
             * 处理预防保健建议
             */
            if (!ObjectUtils.isEmpty(n.getHealthAdvice())) {
                List<HealthAdviceDto> healthAdvice = n.getHealthAdvice().stream().map(m -> {
                    HealthAdviceDto healthAdviceDto = HealthAdviceDto.builder()
                            .title(m.getTitle())
                            .content(m.getContent())
                            .icon(m.getIcon())
                            .iconUrl(!ObjectUtils.isEmpty(m.getIcon()) ? urlMap.get(m.getIcon()) : null)
                            .build();
                    return healthAdviceDto;
                }).collect(Collectors.toList());
                diseaseInfoDto.setHealthAdvice(healthAdvice);
            }


            /**
             * 处理基因位点
             */
            List<LocusInfoDto> locusInfoDtos=n.getLocusInfo().stream().map(n1->{
                LocusInfoDto locusInfoDto=new LocusInfoDto();
                BeanUtils.copyProperties(n1,locusInfoDto);
                locusInfoDto.setLocusImageUrl(!ObjectUtils.isEmpty(n1.getLocusImageId()) ? urlMap.get(n1.getLocusImageId()) : null);
                locusInfoDto.setAllGenType(n1.getAllGeneType());
                return locusInfoDto;
            }).collect(Collectors.toList());
            diseaseInfoDto.setLocusInfo(locusInfoDtos);

            DetectionOfOverviewDto detectionOfOverviewDto = n.getDetectionOfOverview();

            com.zqkh.webapi.context.dto.archive.disease.DetectionOfOverviewDto detectionOfOverview = null;


            if (!ObjectUtils.isEmpty(detectionOfOverviewDto)) {

                MorbidityAgeGroupDto man = detectionOfOverviewDto.getMan();

                MorbidityAgeGroupDto woman = detectionOfOverviewDto.getWoman();

                com.zqkh.webapi.context.dto.archive.disease.MorbidityAgeGroupDto man1 = null;
                if (!ObjectUtils.isEmpty(man)) {
                    man1 = com.zqkh.webapi.context.dto.archive.disease.MorbidityAgeGroupDto.builder()
                            .start(man.getStart())
                            .end(man.getEnd())
                            .build();
                }


                com.zqkh.webapi.context.dto.archive.disease.MorbidityAgeGroupDto woman1 = null;
                if (!ObjectUtils.isEmpty(woman)) {
                    woman1 = com.zqkh.webapi.context.dto.archive.disease.MorbidityAgeGroupDto.builder()
                            .start(woman.getStart())
                            .end(woman.getEnd())
                            .build();
                }

                List<EpidemicAreaDto> epidemicAreaDtoList = detectionOfOverviewDto.getEpidemicArea().stream().map(m -> {
                    EpidemicAreaDto epidemicAreaDto = new EpidemicAreaDto();
                    BeanUtils.copyProperties(m, epidemicAreaDto);
                    return epidemicAreaDto;
                }).collect(Collectors.toList());

                detectionOfOverview = com.zqkh.webapi.context.dto.archive.disease.DetectionOfOverviewDto.builder()
                        .chinaMorbidityMolecule(detectionOfOverviewDto.getChinaMorbidityMolecule())
                        .chinaMorbidityDenominator(detectionOfOverviewDto.getChinaMorbidityDenominator())
                        .man(man1)
                        .woman(woman1)
                        .epidemicArea(epidemicAreaDtoList)
                        .build();
                diseaseInfoDto.setDetectionOfOverview(detectionOfOverview);
            }
            disease.add(diseaseInfoDto);
        });
        geneArchiveInfo.setDisease(disease);
        return geneArchiveInfo;
    }

    @Override
    public GeneArchiveInfoDto getSampleReport(String sex) {
        GeneArchiveInfoDto geneArchiveInfoDto = null;

        if (ObjectUtils.isEmpty(sex)) {
            geneArchiveInfoDto = this.getGeneArchiveInfoByOrderId(sampleReportConfiguration.getWoman().getGeneOrderId());
        } else {
            switch (sex) {
                case "man":
                    geneArchiveInfoDto = this.getGeneArchiveInfoByOrderId(sampleReportConfiguration.getMan().getGeneOrderId());
                    break;
                case "woman":
                    geneArchiveInfoDto = this.getGeneArchiveInfoByOrderId(sampleReportConfiguration.getWoman().getGeneOrderId());
                    break;
                default:
                    geneArchiveInfoDto = this.getGeneArchiveInfoByOrderId(sampleReportConfiguration.getWoman().getGeneOrderId());
                    break;
            }
        }

        geneArchiveInfoDto.setNickName("XXX");
        return geneArchiveInfoDto;
    }
}
