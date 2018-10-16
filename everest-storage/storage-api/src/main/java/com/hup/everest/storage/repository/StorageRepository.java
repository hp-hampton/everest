package com.hup.everest.storage.repository;

import com.hup.everest.storage.model.Storage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @version 1.0
 * @author: hupeng
 * @create: 2018-10-06 21:27
 * @description:
 **/
public interface StorageRepository extends MongoRepository<Storage, String> {
}
