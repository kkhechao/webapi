package com.zqkh.webapi.context.service.impl;

import com.zqkh.file.dto.ResourceInfoDto;
import com.zqkh.file.feign.FileFeign;
import com.zqkh.healthy.feign.TestPaperClient;
import com.zqkh.healthy.feign.TestResultClient;
import com.zqkh.healthy.feign.dto.paper.app.TestPaperInfoToAppDto;
import com.zqkh.healthy.feign.dto.paper.app.TestPaperListToAppDto;
import com.zqkh.healthy.feign.dto.paper.app.TestResultToAppDto;
import com.zqkh.healthy.feign.vo.paper.AnswerVo;
import com.zqkh.webapi.context.auth.AuthManager;
import com.zqkh.webapi.context.constant.BusinessExceptionEnum;
import com.zqkh.webapi.context.domain.dto.test.paper.TestPaperDto;
import com.zqkh.webapi.context.domain.vo.test.paper.SubmitAnAnswerVo;
import com.zqkh.webapi.context.dto.healthy.test.paper.*;
import com.zqkh.webapi.context.exception.BusinessException;
import com.zqkh.webapi.context.jwt.dto.JWTUserDto;
import com.zqkh.webapi.context.service.TestPaperService;
import com.zqkh.webapi.context.utils.RandomDataUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 试卷业务接口实现
 *
 * @author 东来
 * @create 2018/5/9 0009
 */
@Service
public class TestPaperServiceImpl implements TestPaperService {

    @Resource
    private TestPaperClient testPaperClient;

    @Resource
    private FileFeign fileFeign;


    private static final int RECOMMEND_NUM = 6;


    @Resource
    private TestResultClient testResultClient;

    @Override
    public TestPaperListDto getTestPaperList(String familyMemberId, String type) {
        if (ObjectUtils.isEmpty(familyMemberId)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "请选择一个家庭成员");
        }


        List<TestPaperListToAppDto> testPaperListToAppDtoList = testPaperClient.listToApp(familyMemberId, type);

        if (ObjectUtils.isEmpty(testPaperListToAppDtoList)) {
            return null;
        }

        List<String> coverIdList = testPaperListToAppDtoList.stream().map(n -> n.getCoverId()).filter(n -> !ObjectUtils.isEmpty(n)).collect(Collectors.toList());
        List<ResourceInfoDto> resourceInfoDtoList = fileFeign.getResourceInfo(coverIdList);
        Map<String, String> resourceMap = new HashMap<>();
        if (!ObjectUtils.isEmpty(resourceInfoDtoList)) {
            resourceMap = resourceInfoDtoList.stream().collect(Collectors.toSet()).stream().filter(n -> !ObjectUtils.isEmpty(n) && !ObjectUtils.isEmpty(n.getId())).collect(Collectors.toMap(ResourceInfoDto::getId, ResourceInfoDto::getUrl));
        }
        Map<String, String> urlMap = resourceMap;

        List<TestPaperDto> testPaperDtoList = testPaperListToAppDtoList.stream().map(n -> {
            TestPaperDto testPaperDto = new TestPaperDto();
            testPaperDto.setTitle(n.getTitle());
            testPaperDto.setTestResultId(n.getTestResultId());
            testPaperDto.setId(n.getId());
            testPaperDto.setDone(n.getDone());
            testPaperDto.setCoverUrl(urlMap.get(n.getCoverId()));
            return testPaperDto;
        }).collect(Collectors.toList());

        /**
         * 推荐测试
         */
        List<TestPaperDto> recommend = new ArrayList<>();
        /**
         * 其他
         */
        List<TestPaperDto> other = new ArrayList<>();

        if (testPaperDtoList.size() <= RECOMMEND_NUM) {
            recommend.addAll(testPaperDtoList);
        } else {
            recommend = RandomDataUtil.generateRandomDataNoRepeat(testPaperDtoList, RECOMMEND_NUM);
            testPaperDtoList.removeAll(recommend);
            other.addAll(testPaperDtoList);
        }
        TestPaperListDto result = new TestPaperListDto();
        result.setOther(other);
        result.setRecommend(recommend);
        return result;
    }

    @Override
    public TestPaperInfoDto getTestPaperInfo(String id, String familyMemberId) {
        if (ObjectUtils.isEmpty(id)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "试卷编号为空");
        }
        TestPaperInfoToAppDto testPaperInfoToAppDto = testPaperClient.getTestPaperInfoToAppById(id, familyMemberId);
        if (ObjectUtils.isEmpty(testPaperInfoToAppDto)) {
            throw new BusinessException(BusinessExceptionEnum.INVALID_PARAMS.getCode(), "试卷不存在");
        }
        TestPaperInfoDto result = new TestPaperInfoDto();
        result.setId(testPaperInfoToAppDto.getId());
        result.setTitle(testPaperInfoToAppDto.getTitle());
        result.setRemark(testPaperInfoToAppDto.getRemark());
        //处理试题
        List<TestQuestionListDto> question = testPaperInfoToAppDto.getQuestion().stream().map(n -> {
            TestQuestionListDto testQuestionListDto = new TestQuestionListDto();
            testQuestionListDto.setIndex(n.getIndex());
            testQuestionListDto.setRequired(n.getRequired());
            testQuestionListDto.setTitle(n.getTitle());
            //处理选项
            List<TestQuestionOptionDto> option = n.getOption().stream().map(m -> {
                TestQuestionOptionDto testQuestionOptionDto = new TestQuestionOptionDto();
                testQuestionOptionDto.setName(m.getName());
                testQuestionOptionDto.setValue(m.getValue());
                testQuestionOptionDto.setStartIndex(m.getStartIndex());
                testQuestionOptionDto.setEndIndex(m.getEndIndex());
                return testQuestionOptionDto;
            }).collect(Collectors.toList());
            testQuestionListDto.setOption(option);
            return testQuestionListDto;
        }).collect(Collectors.toList());
        result.setQuestion(question);

        //处理用户答题结果
        if (!ObjectUtils.isEmpty(testPaperInfoToAppDto.getTestResult())) {
            List<FamilyMemberTestResultDto> testResult = testPaperInfoToAppDto.getTestResult().stream().map(n -> {
                FamilyMemberTestResultDto familyMemberTestResultDto = new FamilyMemberTestResultDto();
                familyMemberTestResultDto.setIndex(n.getIndex());
                familyMemberTestResultDto.setOptionName(n.getOptionName());
                familyMemberTestResultDto.setPreviousIndex(n.getPreviousIndex());
                return familyMemberTestResultDto;
            }).collect(Collectors.toList());
            result.setTestResult(testResult);
        }
        if (!ObjectUtils.isEmpty(testPaperInfoToAppDto.getTestResultId())) {
            result.setTestResultId(testPaperInfoToAppDto.getTestResultId());
        }
        return result;
    }

    @Override
    public TestResultDto getTestResult(String id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "答题结果编号为空");
        }

        TestResultToAppDto testResultToAppDto = testResultClient.getTestResultToApp(id);

        TestResultDto testResultDto = this.getTestResultDto(testResultToAppDto);

        return testResultDto;
    }

    @Override
    public TestResultDto submitAnAnswer(SubmitAnAnswerVo submitAnAnswerVo) {
        if (ObjectUtils.isEmpty(submitAnAnswerVo)) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "答题参数为空");
        }

        if (ObjectUtils.isEmpty(submitAnAnswerVo.getId())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "请选择一个测试试卷");
        }

        if (ObjectUtils.isEmpty(submitAnAnswerVo.getFamilyMemberId())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "请选择一个家庭成员");
        }
        if (ObjectUtils.isEmpty(submitAnAnswerVo.getAnswer())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "答题");
        }

        if (ObjectUtils.isEmpty(submitAnAnswerVo.getAnswer())) {
            throw new BusinessException(BusinessExceptionEnum.PARAM_IS_NULL.getCode(), "答案为空");
        }
        JWTUserDto userDto = AuthManager.currentUser();


        com.zqkh.healthy.feign.vo.paper.SubmitAnAnswerVo submitAnAnswerVo1 = new com.zqkh.healthy.feign.vo.paper.SubmitAnAnswerVo();
        submitAnAnswerVo1.setId(submitAnAnswerVo.getId());
        submitAnAnswerVo1.setUserId(userDto.getUserId());
        submitAnAnswerVo1.setFamilyMemberId(submitAnAnswerVo.getFamilyMemberId());

        List<AnswerVo> answer = submitAnAnswerVo.getAnswer().stream().map(n -> {
            AnswerVo answerVo = new AnswerVo();
            BeanUtils.copyProperties(n, answerVo);
            return answerVo;
        }).collect(Collectors.toList());
        submitAnAnswerVo1.setAnswer(answer);
        TestResultToAppDto testResultToAppDto = testResultClient.submitAnAnswer(submitAnAnswerVo1);

        TestResultDto testResultDto = this.getTestResultDto(testResultToAppDto);

        return testResultDto;

    }


    private TestResultDto getTestResultDto(TestResultToAppDto testResultToAppDto) {
        if (ObjectUtils.isEmpty(testResultToAppDto)) {
            return null;
        }
        TestResultDto testResultDto = new TestResultDto();
        testResultDto.setId(testResultToAppDto.getId());
        testResultDto.setTitle(testResultToAppDto.getTitle());
        testResultDto.setTestResultId(testResultToAppDto.getTestResultId());

        if (!ObjectUtils.isEmpty(testResultToAppDto.getPoint())) {

            Set<String> resourceSet = new HashSet<>();

            List<String> resourceId = testResultToAppDto.getPoint().stream().map(n -> n.getCoverId()).filter(n -> !ObjectUtils.isEmpty(n)).collect(Collectors.toList());

            resourceSet.addAll(resourceId);

            List<ResourceInfoDto> resourceInfoDtoList = fileFeign.getResourceInfo(resourceSet.stream().collect(Collectors.toList()));


            Map<String, String> resourceMap = new HashMap<>();
            if (!ObjectUtils.isEmpty(resourceInfoDtoList)) {
                resourceMap = resourceInfoDtoList.stream().filter(n -> !ObjectUtils.isEmpty(n) && !ObjectUtils.isEmpty(n.getId())).collect(Collectors.toSet()).stream().collect(Collectors.toMap(ResourceInfoDto::getId, ResourceInfoDto::getUrl));
            }
            Map<String, String> urlMap = resourceMap;

            List<TestResultPointDto> point = testResultToAppDto.getPoint().stream().map(n -> {
                TestResultPointDto testResultPointDto = new TestResultPointDto();
                testResultPointDto.setCoverUrl(urlMap.get(n.getCoverId()));
                testResultPointDto.setName(n.getName());
                testResultPointDto.setResult(n.getResult());
                testResultPointDto.setExplain(n.getExplain());
                testResultPointDto.setProgramId(n.getProgramId());
                if (!ObjectUtils.isEmpty(n.getProgramStatus())) {
                    testResultPointDto.setProgramStatus(TestResultPointDto.ProgramStatus.getStatus(n.getProgramStatus().name()));
                }
                testResultPointDto.setProgramResultId(n.getProgramResultId());
                return testResultPointDto;
            }).collect(Collectors.toList());

            testResultDto.setPoint(point);
        }

        return testResultDto;

    }

}
