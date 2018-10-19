package com.hup.everest.storage.controller;

import com.hup.everest.storage.service.StorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author hupeng on 2018/10/16
 * @version 1.0
 * @Description
 */
@RestController
@RequestMapping("/api/storage")
@Api(tags = "文件上传服务")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public String upload(MultipartFile multipartFile) {
        return storageService.upload(multipartFile);
    }

    @GetMapping("/download")
    @ApiOperation("下载文件")
    public ResponseEntity download(@RequestParam("objectId") String objectId) {
        return storageService.download(objectId);
    }

    @GetMapping("/preview")
    @ApiOperation("预览文件")
    public ResponseEntity preview(@RequestParam("objectId") String objectId) {
        return storageService.preview(objectId);
    }

}
