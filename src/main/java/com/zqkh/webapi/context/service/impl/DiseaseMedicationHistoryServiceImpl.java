package com.zqkh.webapi.context.service.impl;

import com.google.common.collect.Lists;
import com.zqkh.archive.feign.DiseaseMedicationHistoryClient;
import com.zqkh.archive.feign.dto.DiseaseMedicationHistoryDto;
import com.zqkh.archive.feign.dto.MedicationHistoryDto;
import com.zqkh.archive.feign.enums.MedicationType;
import com.zqkh.archive.feign.vo.DiseaseMedicationHistoryVo;
import com.zqkh.archive.feign.vo.MedicationHistoryVo;
import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.domain.dto.healthhistory.disease.ApiDiseaseMedicationHistoryDto;
import com.zqkh.webapi.context.domain.dto.healthhistory.disease.ApiMedicationHistoryDto;
import com.zqkh.webapi.context.domain.enums.ApiMedicationType;
import com.zqkh.webapi.context.domain.vo.healthhistory.disease.ApiDiseaseMedicationHistoryPageVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.disease.ApiDiseaseMedicationHistoryVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.disease.ApiMedicationHistoryVo;
import com.zqkh.webapi.context.domain.vo.healthhistory.drug.ApiDrugItem;
import com.zqkh.webapi.context.service.DiseaseMedicationHistoryService;
import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 疾病医药史 Service
 */
@Service
public class DiseaseMedicationHistoryServiceImpl implements DiseaseMedicationHistoryService {

    @Autowired
    private DiseaseMedicationHistoryClient client;

    private DozerBeanMapper modelMapper = new DozerBeanMapper();

    @Override
    public PageResult<ApiDiseaseMedicationHistoryDto> getDiseaseMedicationHistories(ApiDiseaseMedicationHistoryPageVo inVo) {
        DiseaseMedicationHistoryVo transVo = modelMapper.map(inVo, DiseaseMedicationHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        PageResult<DiseaseMedicationHistoryDto> resDtos = client.getDiseaseMedicationHistories(transVo);

        //region 将 Fegin 的响应结果，转换为 API 的响应 Dto
        List<ApiDiseaseMedicationHistoryDto> outDtos = resDtos.getList()
                .stream()
                .map(resDto -> {
                    ApiDiseaseMedicationHistoryDto outDto = new ApiDiseaseMedicationHistoryDto();
                    modelMapper.map(resDto, outDto);

                    List<MedicationHistoryDto> medicationHistories = resDto.getMedicationHistories();

                    if (CollectionUtils.isNotEmpty(medicationHistories)) {
                        MedicationHistoryDto baseMedicationHistoryDto = medicationHistories.get(0);

                        //region 将用药史列表映射为单对象实体

                        ApiMedicationHistoryDto apiMedicationHistoryDto = new ApiMedicationHistoryDto();

                        apiMedicationHistoryDto.setStartTime(baseMedicationHistoryDto.getStartTime());
                        apiMedicationHistoryDto.setEndTime(baseMedicationHistoryDto.getEndTime());

                        if (!Objects.isNull(baseMedicationHistoryDto.getEffect())) {
                            apiMedicationHistoryDto.setEffect(ApiMedicationHistoryDto.Effect.valueOf(baseMedicationHistoryDto.getEffect().name()));
                        }

                        if (!Objects.isNull(baseMedicationHistoryDto.getMedicationType())) {
                            apiMedicationHistoryDto.setMedicationType(ApiMedicationType.valueOf(baseMedicationHistoryDto.getMedicationType().name()));
                        }

                        List<ApiDrugItem> drugs = medicationHistories.stream()
                                .map(item -> new ApiDrugItem(item.getDrug(), item.getDrugName(), ApiMedicationType.valueOf(item.getMedicationType().name())))
                                .collect(Collectors.toList());

                        apiMedicationHistoryDto.setDrugs(drugs);

                        outDto.setMedicationHistory(apiMedicationHistoryDto);
                        //endregion
                    }

                    return outDto;
                }).collect(Collectors.toList());
        //endregion

        return new PageResult<>(resDtos.getTotalCount(), resDtos.getPageSize(), outDtos);
    }

    @Override
    public void addDiseaseMedicationHistory(ApiDiseaseMedicationHistoryVo inVo) {
        DiseaseMedicationHistoryVo transVo = modelMapper.map(inVo, DiseaseMedicationHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        //region 将用药史单实体映射为多实体
        singleToListVoOfMedicationHistory(inVo, transVo);
        //endregion

        client.addDiseaseMedicationHistory(transVo);
    }

    @Override
    public void editDiseaseMedicationHistory(ApiDiseaseMedicationHistoryVo inVo) {
        DiseaseMedicationHistoryVo transVo = modelMapper.map(inVo, DiseaseMedicationHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        //region 将用药史单实体映射为多实体
        singleToListVoOfMedicationHistory(inVo, transVo);
        //endregion

        client.editDiseaseMedicationHistory(transVo);
    }

    @Override
    public void editMedicationHistories(ApiDiseaseMedicationHistoryVo inVo) {
        DiseaseMedicationHistoryVo transVo = modelMapper.map(inVo, DiseaseMedicationHistoryVo.class);
        transVo.setUserId(AuthManager.currentUser().getUserId());

        //region 将用药史单实体映射为多实体
        singleToListVoOfMedicationHistory(inVo, transVo);
        //endregion

        client.editMedicationHistories(transVo);
    }

    @Override
    public void deleteDiseaseMedicationHistory(String id) {
        client.deleteDiseaseMedicationHistory(id);
    }

    /**
     * 将用药史单实体映射为多实体
     */
    private void singleToListVoOfMedicationHistory(ApiDiseaseMedicationHistoryVo inVo, DiseaseMedicationHistoryVo transVo) {
        ApiMedicationHistoryVo medicationHistory = inVo.getMedicationHistory();

        if (!Objects.isNull(medicationHistory)) {
            List<ApiDrugItem> drugs = medicationHistory.getDrugs();

            if (CollectionUtils.isNotEmpty(drugs)) {
                List<MedicationHistoryVo> medicationHistoryVos = drugs.stream()
                        .map(drugItem -> {
                            MedicationHistoryVo medicationHistoryVo = new MedicationHistoryVo();

                            medicationHistoryVo.setStartTime(medicationHistory.getStartTime());
                            medicationHistoryVo.setEndTime(medicationHistory.getEndTime());

                            if (!Objects.isNull(medicationHistory.getEffect())) {
                                medicationHistoryVo.setEffect(MedicationHistoryVo.Effect.valueOf(medicationHistory.getEffect().name()));
                            }

                            if (!Objects.isNull(drugItem.getType())) {
                                medicationHistoryVo.setMedicationType(MedicationType.valueOf(drugItem.getType().name()));
                            }

                            medicationHistoryVo.setDrug(
                                    Objects.equals(medicationHistoryVo.getMedicationType(), MedicationType.RECOMMEND) ? drugItem.getId() : drugItem.getName()
                            );

                            return medicationHistoryVo;
                        })
                        .collect(Collectors.toList());

                transVo.setMedicationHistories(medicationHistoryVos);
            } else {
                MedicationHistoryVo medicationHistoryVo = new MedicationHistoryVo();
                medicationHistoryVo.setStartTime(medicationHistory.getStartTime());
                medicationHistoryVo.setEndTime(medicationHistory.getEndTime());

                if (!Objects.isNull(medicationHistoryVo.getEffect())) {
                    medicationHistoryVo.setEffect(MedicationHistoryVo.Effect.valueOf(medicationHistory.getEffect().name()));
                }

                transVo.setMedicationHistories(Lists.newArrayList(medicationHistoryVo));
            }
        }
    }


}
