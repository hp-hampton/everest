package com.hup.everest.storage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hup.everest.storage.model.Storage;
import com.hup.everest.storage.repository.StorageRepository;
import com.hup.everest.storage.service.StorageService;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @version 1.0
 * @author: hupeng
 * @create: 2018-10-06 21:23
 * @description:
 **/
@Service(version = "1.0.0")
public class StorageServiceImpl implements StorageService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private MongoDbFactory mongoDbFactory;


    public String upload(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectId store = gridFsTemplate.store(inputStream, originalFilename, contentType);
        return store.toString();
    }

    public ResponseEntity download(String objectId) {
        Storage storage = getStorage(objectId);
        InputStreamResource inputStreamResource = new InputStreamResource(storage.getContent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", storage.getName()))
                .header("name", storage.getName())
                .header("contentType", storage.getContentType())
                .body(inputStreamResource);
    }

    public ResponseEntity preview(String objectId) {
        Storage storage = getStorage(objectId);
        InputStreamResource inputStreamResource = new InputStreamResource(storage.getContent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, storage.getContentType())
                .body(inputStreamResource);
    }

    private Storage getStorage(String objectId) {
        Query query = Query.query(Criteria.where("_id").is(objectId));
        GridFSFile gridFSFile = gridFsTemplate.findOne(query);
        String fileName = gridFSFile.getFilename().replace(",", "");
        GridFSBucket bucket = GridFSBuckets.create(mongoDbFactory.getDb());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bucket.downloadToStream(gridFSFile.getId(), byteArrayOutputStream);
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        Storage storage = new Storage();
        storage.setName(fileName);
        storage.setContentType(gridFSFile.getMetadata().get("_contentType").toString());
        storage.setLength(gridFSFile.getLength());
        storage.setChunkSize(gridFSFile.getChunkSize());
        storage.setUploadDate(gridFSFile.getUploadDate());
        storage.setMd5(gridFSFile.getMD5());
        storage.setContent(arrayInputStream);
        return storage;
    }
}
