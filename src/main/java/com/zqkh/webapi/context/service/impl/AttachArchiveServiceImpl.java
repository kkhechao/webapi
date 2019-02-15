package com.zqkh.webapi.context.service.impl;

import com.zqkh.archive.feign.AttachArchiveClient;
import com.zqkh.archive.feign.dto.AttachArchiveDto;
import com.zqkh.archive.feign.dto.AttachArchiveReqDto;
import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.service.AttachArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wenjie
 * @date 2018/1/30 0030 18:45
 */
@Service
public class AttachArchiveServiceImpl implements AttachArchiveService {

    @Autowired
    private AttachArchiveClient attachArchiveClient;

    @Override
    public PageResult<AttachArchiveDto> getAttachArchives(AttachArchiveReqDto attachArchiveReqDto) {
        return attachArchiveClient.getAttachArchive(attachArchiveReqDto);
    }

    @Override
    public void addAttachArchive(AttachArchiveDto attachArchiveDto) {
        attachArchiveClient.addAttachArchive(attachArchiveDto);
    }

    @Override
    public void updateAttachArchive(AttachArchiveDto attachArchiveDto) {
        attachArchiveClient.editAttachArchive(attachArchiveDto);
    }

    @Override
    public void delAttachArchive(String id) {
        attachArchiveClient.delAttachArchive(id);
    }
}
