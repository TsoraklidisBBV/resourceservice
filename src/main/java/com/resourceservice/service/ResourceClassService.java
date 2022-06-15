package com.resourceservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.resourceservice.exception.ResourceClassBadRequestException;
import com.resourceservice.exception.ResourceClassNotFoundException;
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

    public ResourceClassDTO createResourceClass(CreateResourceClassDTO createResourceClassDTO) {
        ResourceClassEntity resourceClassEntity = new ResourceClassEntity();
        if (createResourceClassDTO.getName() == null || createResourceClassDTO.getName().isEmpty()) {
            throw new ResourceClassBadRequestException("Name should not be null");
        }

        resourceClassEntity.setName(createResourceClassDTO.getName());
        resourceClassEntity.setUuid(UUID.randomUUID().toString());

        ResourceClassEntity savedResourceClass = resourceClassRepository.save(resourceClassEntity);

        ResourceClassDTO result = new ResourceClassDTO();
        result.setName(savedResourceClass.getName());
        result.setUuid(savedResourceClass.getUuid());
        return result;
    }

    public ResourceClassDTO updateResourceClass(UpdateResourceClassDTO updateResourceClassDTO, String uuid) {
        Optional<ResourceClassEntity> resourceClassEntity = resourceClassRepository.findByUuid(uuid);
        if (resourceClassEntity.isEmpty()) {
            System.out.println("There is no Resource with UUID: " + uuid);
            throw new ResourceClassNotFoundException(uuid);
        }

        if (updateResourceClassDTO.getName() == null || updateResourceClassDTO.getName().isEmpty()) {
            throw new ResourceClassBadRequestException("Name should not be null");
        }
        resourceClassEntity.get().setName(updateResourceClassDTO.getName());
        ResourceClassEntity savedResourceClassEntity = resourceClassRepository.save(resourceClassEntity.get());

        ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
        resourceClassDTO.setName(savedResourceClassEntity.getName());
        resourceClassDTO.setUuid(uuid);
        return resourceClassDTO;
    }

    public List<ResourceClassDTO> getAllResourceClass() {
        List<ResourceClassEntity> resourceClassEntityList = resourceClassRepository.findAll();
        if (resourceClassEntityList.isEmpty()) {
            System.out.println("There is no Resource");
            throw new ResourceClassNotFoundException(null);
        }
        return mapResourceClassEntityToDTO(resourceClassEntityList);
    }

    public ResourceClassDTO getByUuidResourceClass(String uuid) {
        Optional<ResourceClassEntity> resourceClassEntity = resourceClassRepository.findByUuid(uuid);
        if (resourceClassEntity.isEmpty()) {
            System.out.println("There is no Resource with UUID: " + uuid);
            throw new ResourceClassNotFoundException(uuid);
        }

        return entityToClassDTO(resourceClassEntity.get());
    }

    public long deleteByUuidResourceClass(String uuid) {
        long result = resourceClassRepository.deleteByUuid(uuid);
        if (result != 1L) {
            System.out.println("Error trying to delete Resource Class with " + uuid);
            throw new ResourceClassNotFoundException(uuid);
        }

        return result;
    }

    public ResourceClassService(ResourceClassRepository resourceClassRepository) {
        this.resourceClassRepository = resourceClassRepository;
    }

    private ResourceClassDTO entityToClassDTO(ResourceClassEntity resourceClassEntity) {
        ResourceClassDTO resourceClassObject = new ResourceClassDTO();
        resourceClassObject.setName(resourceClassEntity.getName());
        resourceClassObject.setUuid(resourceClassEntity.getUuid());

        return resourceClassObject;
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

}