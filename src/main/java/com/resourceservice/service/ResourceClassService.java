package com.resourceservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.resourceservice.model.CreateResourceClassDTO;
import com.resourceservice.model.ResourceClassDTO;
import com.resourceservice.model.UpdateResourceClassDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resourceservice.model.ResourceClassEntity;
import com.resourceservice.repository.ResourceClassRepository;

@Service
public class ResourceClassService {
    @Autowired
    private ResourceClassRepository resourceClassRepository;

    public ResourceClassDTO createResourceClass(CreateResourceClassDTO resourceClassDTO) {
        ResourceClassEntity resourceClassEntity = new ResourceClassEntity();
        resourceClassEntity.setName(resourceClassDTO.getName());
        resourceClassEntity.setUuid(UUID.randomUUID().toString());
        ResourceClassEntity savedResourceClass = resourceClassRepository.save(resourceClassEntity);

        ResourceClassDTO result = new ResourceClassDTO();
        result.setName(savedResourceClass.getName());
        result.setUuid(savedResourceClass.getUuid());
        return result;
    }

    public ResourceClassDTO updateResourceClass(UpdateResourceClassDTO updateResourceClassDTO, String uuid) {
        ResourceClassEntity resourceClassEntity = resourceClassRepository.findByUuid(uuid).get(0);
        resourceClassEntity.setName(updateResourceClassDTO.getName());
        ResourceClassEntity savedResourceClassEntity = resourceClassRepository.save(resourceClassEntity);

        ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
        resourceClassDTO.setName(savedResourceClassEntity.getName());
        resourceClassDTO.setUuid(uuid);
        return resourceClassDTO;
    }

    public List<ResourceClassDTO> getAllResourceClass() {
        List<ResourceClassEntity> resourceClassEntityList = resourceClassRepository.findAll();
        return mapResourceClassEntityToDTO(resourceClassEntityList);
    }

    public ResourceClassDTO getByUuidResourceClass(String uuid) {
        List<ResourceClassEntity> resourceClassEntityList = resourceClassRepository.findByUuid(uuid);
        return mapResourceClassEntityToDTO(resourceClassEntityList).get(0);
    }

    private List<ResourceClassDTO> mapResourceClassEntityToDTO(List<ResourceClassEntity> resourceClassEntityList) {
        if (resourceClassEntityList.isEmpty()) {
            System.out.println("There is no Resource Class to return");
            return null;
        }

        List<ResourceClassDTO> resourceClassDTOList = new ArrayList<>();

        for (ResourceClassEntity resourceClassModel : resourceClassEntityList) {
            ResourceClassDTO resourceClassObjects = new ResourceClassDTO();

            resourceClassObjects.setName(resourceClassModel.getName());
            resourceClassObjects.setUuid(resourceClassModel.getUuid());
            resourceClassDTOList.add(resourceClassObjects);
        }

        return resourceClassDTOList;
    }

    public long deleteByUuidResourceClass(String uuid) {
        return resourceClassRepository.deleteByUuid(uuid);
    }

    public ResourceClassService(ResourceClassRepository resourceClassRepository) {
        this.resourceClassRepository = resourceClassRepository;
    }

}