package com.resourceservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.resourceservice.model.ResourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resourceservice.model.ResourceClassEntity;
import com.resourceservice.repository.ResourceClassRepository;

@Service
public class ResourceClassService {
    @Autowired
    private ResourceClassRepository resourceClassRepository;

    public ResourceClassEntity createResourceClass(CreateResourceClassDTO resourceClassDTO) {
        ResourceClassEntity result = new ResourceClassEntity();
        result.setName(resourceClassDTO.getName());
        result.setUuid(UUID.randomUUID().toString());
        resourceClassRepository.save(result);
        return result;
    }

    public List<ResourceClassEntity> getAllResourceClass() {
        return resourceClassRepository.findAll();
    }

    public ResourceClassEntity getByUuidResourceClass(String uuid) {
        List<ResourceClassEntity> result = resourceClassRepository.getByUuid(uuid);

        if (result.isEmpty()) {
            System.out.println("There is no Resource for uuid:" + uuid);
            return null;
        }

        return result.get(0);
    }

    public long deleteByUuidResourceClass(String uuid) {
        return resourceClassRepository.deleteByUuid(uuid);
    }
}