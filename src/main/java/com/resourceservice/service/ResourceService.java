package com.resourceservice.service;

import com.resourceservice.model.ResourceClassEntity;
import com.resourceservice.model.ResourceEntity;
import com.resourceservice.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository repository;

//    public ResourceEntity createResource(CreateResourceDTO resourceDTO) {
//        ResourceEntity result = new ResourceEntity();
//        result.setName(resourceDTO.getName());
//        result.setUuid(UUID.randomUUID().toString());
//        repository.save(result);
//        return result;
//    }

    public ResourceEntity getResourcesByUuid(String resourceUuid) throws IOException {
        List<ResourceEntity> modelList = repository.findByUuid(resourceUuid);
        return findResourceByUuid(modelList, resourceUuid);
    }

    public long deleteResourcesByUuid(String resourceUuid) {
        return repository.deleteByUuid(resourceUuid);
    }

    private ResourceEntity findResourceByUuid(List<ResourceEntity> modelList, String resourceUuid) {
        if (modelList.isEmpty()) {
            System.out.println("Wrong uuid");
            return null;
        }

        return modelList.get(0);
    }
}
