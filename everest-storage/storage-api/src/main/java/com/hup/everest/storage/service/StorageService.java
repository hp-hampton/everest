package com.hup.everest.storage.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @version 1.0
 * @author: hupeng
 * @create: 2018-10-06 21:16
 * @description:
 **/
public interface StorageService {

    String upload(MultipartFile multipartFile);

    ResponseEntity download(String objectId);

    ResponseEntity preview(String objectId);
}
