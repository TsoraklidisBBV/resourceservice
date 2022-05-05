package com.resourceservice.service;

import com.resourceservice.model.ResourceEntity;
import com.resourceservice.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository repository;

    public ResourceEntity getResourcesByName(String resourceUuid) throws IOException {
        List<ResourceEntity> modelList = repository.findByUuid(resourceUuid);
        return findResourceByUuid(modelList, resourceUuid);
    }

    public void deleteResourcesByName(String resourceUuid) {
        // List<ResourceModel> modelList = repository.findByUser(resourceUuid);

        //  findUser(modelList, resourceUuid);
        //   Long deletedItems =
        repository.deleteByUuid(resourceUuid);
//        if (deletedItems != 1){
//            return null;
//        }

        //   return true;
    }

    private ResourceEntity findResourceByUuid(List<ResourceEntity> modelList, String resourceUuid) {
        if (modelList.isEmpty()) {
            System.out.println("Wrong name");
            return null;
        }

        return modelList.get(0);
    }
}
