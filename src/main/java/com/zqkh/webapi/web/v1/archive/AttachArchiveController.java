package com.zqkh.webapi.web.v1.archive;

import com.zqkh.archive.feign.dto.AttachArchiveDto;
import com.zqkh.archive.feign.dto.AttachArchiveReqDto;
import com.zqkh.common.result.PageResult;
import com.zqkh.webapi.context.auth.Anonymous;
import com.zqkh.webapi.context.service.AttachArchiveService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wenjie
 * @date 2018/1/30 0030 18:42
 */
@RestController
@RequestMapping("/archive/attach")
public class AttachArchiveController {


    @Autowired
    private AttachArchiveService attachArchiveService;

    @GetMapping("/")
    @Anonymous
    @ApiOperation(value = "api_archive_attach_get")
    public PageResult<AttachArchiveDto> getAttachArchive(AttachArchiveReqDto attachArchiveReqDto) {
        return attachArchiveService.getAttachArchives(attachArchiveReqDto);
    }

    @PostMapping("/add")
    @Anonymous
    @ApiOperation(value = "api_archive_attach_add")
    public void addAttachArchive(@RequestBody AttachArchiveDto attachArchiveDto) {
        attachArchiveService.addAttachArchive(attachArchiveDto);
    }

    @PostMapping("/edit")
    @Anonymous
    @ApiOperation(value = "api_archive_attach_edit")
    public void editAttachArchive(@RequestBody AttachArchiveDto attachArchiveDto) {
        attachArchiveService.updateAttachArchive(attachArchiveDto);
    }

    @PostMapping("/del")
    @Anonymous
    @ApiOperation(value = "api_archive_attach_del")
    public void editAttachArchive(@RequestBody String id) {
        attachArchiveService.delAttachArchive(id);
    }

}
