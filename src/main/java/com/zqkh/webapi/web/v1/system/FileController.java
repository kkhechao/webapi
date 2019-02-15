package com.zqkh.webapi.web.v1.system;


import com.zqkh.file.feign.FileFeign;
import com.zqkh.file.feign.FileFeignUtils;
import com.zqkh.webapi.context.auth.Anonymous;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FileController {

    @Autowired
    FileFeign filefeignService;

    @Autowired
    private DiscoveryClient discoveryClient;

//    @Anonymous
//    @RequestMapping(value = "/imgUpload", method = RequestMethod.POST)
//    @ApiOperation(value = "api_imgUpload", notes = "OSS图片上传请求")
//    @ApiImplicitParams({@ApiImplicitParam(name = "imgBase64", value = "图片base64", required = true, dataType = "String"), @ApiImplicitParam(name = "contentType", value = "图片类型", required = true, dataType = "String")})
//    public FileIndexDto upload(@RequestBody ImgDto imgDto) {
//
//        byte[] b = Img2Base64Util.toByte(imgDto.getImgBase64());
//        if (b == null) {
//            return new FileIndexDto();
//        }
//        FileIndexDto fileIndexDto = filefeignService.updateFile(b, imgDto.getContentType(), null);
//        return fileIndexDto;
//
//    }


    @Anonymous
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "fileUpload", notes = "OSS文件上传请求")
    public Object fileUpload(MultipartFile[] file) throws IllegalAccessException, IntrospectionException, InvocationTargetException, IOException {


        if (file != null && file.length > 0) {
            List<Object> list = new ArrayList(file.length);
            for (MultipartFile multipartFile : file) {

                FileFeign fileFeign = FileFeignUtils.getFileFeignClient(discoveryClient);
                //FileIndexDto fileIndexDto = filefeignService.updateFile(multipartFile.getBytes(), multipartFile.getContentType(), null);
                Object upload = fileFeign.upload(multipartFile);

                list.add(upload);
            }
            return list;
        }
        return null;
    }
}
