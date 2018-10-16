package com.hup.everest.storage.controller;

import com.hup.everest.storage.service.StorageService;
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
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public String upload(MultipartFile multipartFile) {
        return storageService.upload(multipartFile);
    }

    @GetMapping("/download")
    public ResponseEntity download(@RequestParam("objectId") String objectId) {
        return storageService.download(objectId);
    }

    @GetMapping("/preview")
    public ResponseEntity preview(@RequestParam("objectId") String objectId) {
        return storageService.preview(objectId);
    }

}
