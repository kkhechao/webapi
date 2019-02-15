package com.zqkh.webapi.context.service.impl;

import com.zqkh.file.dto.ResourceInfoDto;
import com.zqkh.file.feign.FileFeign;
import com.zqkh.healthy.feign.TestPaperTypeClient;
import com.zqkh.healthy.feign.dto.paper.app.type.TestPaperTypeListToAppDto;
import com.zqkh.webapi.context.domain.dto.test.paper.TestPaperDto;
import com.zqkh.webapi.context.domain.dto.test.paper.TestPaperTypeListDto;
import com.zqkh.webapi.context.service.TestPaperTypeService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 测题类型业务实现
 *
 * @author 东来
 * @create 2018/6/7 0007
 */
@Service
public class TestPaperTypeServiceImpl implements TestPaperTypeService {

    @Resource
    private TestPaperTypeClient testPaperTypeClient;

    @Resource
    private FileFeign fileFeign;

    @Override
    public List<TestPaperTypeListDto> list(String familyMemberId) {

        List<TestPaperTypeListToAppDto> taperTypeListToApp = testPaperTypeClient.getTaperTypeListToApp(familyMemberId);
        if (ObjectUtils.isEmpty(taperTypeListToApp)) {
            return Collections.EMPTY_LIST;
        }
        Set<String> resourceSet = new HashSet<>();
        taperTypeListToApp.forEach(n -> {
            if (!ObjectUtils.isEmpty(n.getTestPaper())) {
                Set<String> resourceIds = n.getTestPaper().stream().map(m -> m.getCoverId()).filter(m -> !ObjectUtils.isEmpty(m)).collect(Collectors.toSet());
                resourceSet.addAll(resourceIds);
            }
        });

        List<ResourceInfoDto> resourceInfoDtoList = fileFeign.getResourceInfo(resourceSet.stream().collect(Collectors.toList()));

        Map<String, String> resourceMap = new HashMap<>();
        if (!ObjectUtils.isEmpty(resourceInfoDtoList)) {
            resourceMap = resourceInfoDtoList.stream().collect(Collectors.toMap(ResourceInfoDto::getId, ResourceInfoDto::getUrl));
        }
        Map<String, String> urlMap = resourceMap;

        List<TestPaperTypeListDto> result = taperTypeListToApp.stream().map(n -> {
            TestPaperTypeListDto testPaperTypeListDto = new TestPaperTypeListDto();
            testPaperTypeListDto.setId(n.getId());
            testPaperTypeListDto.setDesc(n.getDesc());
            testPaperTypeListDto.setName(n.getName());
            testPaperTypeListDto.setTemplate(n.getTemplate());
            List<TestPaperDto> testPaperDtoList = n.getTestPaper().stream().map(m -> {
                TestPaperDto testPaperDto = new TestPaperDto();
                testPaperDto.setCoverUrl(urlMap.get(m.getId()));
                testPaperDto.setDone(m.getDone());
                testPaperDto.setId(m.getId());
                testPaperDto.setTestResultId(m.getTestResultId());
                testPaperDto.setTitle(m.getTitle());
                return testPaperDto;
            }).collect(Collectors.toList());
            testPaperTypeListDto.setTestPaper(testPaperDtoList);
            return testPaperTypeListDto;
        }).collect(Collectors.toList());
        return result;
    }
}
