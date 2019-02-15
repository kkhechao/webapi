package com.zqkh.webapi.context.service;

import com.zqkh.archive.feign.dto.AttachArchiveDto;
import com.zqkh.archive.feign.dto.AttachArchiveReqDto;
import com.zqkh.common.result.PageResult;

/**
 * @author wenjie
 * @date 2018/1/30 0030 18:44
 */
public interface AttachArchiveService {
    PageResult<AttachArchiveDto> getAttachArchives(AttachArchiveReqDto attachArchiveReqDto);

    void addAttachArchive(AttachArchiveDto attachArchiveDto);

    void updateAttachArchive(AttachArchiveDto attachArchiveDto);

    void delAttachArchive(String id);
}
