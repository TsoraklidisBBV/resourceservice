package com.resourceservice.service;

import com.resourceservice.exception.ResourceBadRequestException;
import com.resourceservice.exception.ResourceClassBadRequestException;
import com.resourceservice.exception.ResourceClassNotFoundException;
import com.resourceservice.exception.ResourceNotFoundException;
import com.resourceservice.model.CreateResourceDTO;
import com.resourceservice.model.ResourceClassDTO;
import com.resourceservice.model.ResourceClassEntity;
import com.resourceservice.model.ResourceDTO;
import com.resourceservice.model.ResourceEntity;
import com.resourceservice.model.UpdateResourceDTO;
import com.resourceservice.repository.ResourceClassRepository;
import com.resourceservice.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository repository;

    @Autowired
    private ResourceClassRepository resourceClassRepository;

    public ResourceDTO createResource(CreateResourceDTO createResourceDTO) throws IOException {
        ResourceEntity resourceEntity = new ResourceEntity();
        resourceEntity.setName(createResourceDTO.getName());
        resourceEntity.setUuid(UUID.randomUUID().toString());
        resourceEntity.setDescription(createResourceDTO.getDescription());

        Optional<ResourceClassEntity> resourceClassEntityList = resourceClassRepository
                .findByUuid(createResourceDTO.getResourceClassUuid());
        if (resourceClassEntityList.isEmpty()) {
            System.out.println("There is no Resource Class to return");
            throw new ResourceClassBadRequestException(createResourceDTO.getResourceClassUuid());
        }

        resourceEntity.setResourceClassEntity(resourceClassEntityList.get());
        ResourceEntity savedResourceEntity = repository.save(resourceEntity);
        //TODO: does this need to throw an exception?

        ResourceClassDTO resourceClassDTO = entityToClassDTO(resourceEntity.getResourceClassEntity());
        ResourceDTO result = entityToDTO(savedResourceEntity);
        result.setResourceClassDTO(resourceClassDTO);
        return result;
    }

    public ResourceDTO updateResource(UpdateResourceDTO updateResourceDTO, String resourceUuid) {
        Optional<ResourceEntity> resourceEntity = repository.findByUuid(resourceUuid);
        if (resourceEntity.isEmpty()) {
            System.out.println("There is no Resource with UUID: " + resourceUuid);
            throw new ResourceNotFoundException(resourceUuid);
        }

        resourceEntity.get().setName(updateResourceDTO.getName());
        ResourceEntity savedResourceEntity = repository.save(resourceEntity.get());
        ResourceClassDTO resourceClassDTO = entityToClassDTO(resourceEntity.get().getResourceClassEntity());
        ResourceDTO result = entityToDTO(savedResourceEntity);
        result.setResourceClassDTO(resourceClassDTO);
        return result;
    }

    public List<ResourceDTO> getAllResource() {
        List<ResourceEntity> resourceEntityList = repository.findAll();
        if (resourceEntityList.isEmpty()) {
            System.out.println("There are no Resources");
            throw new ResourceNotFoundException(null);
        }
        return mapResourceEntityToDTO(resourceEntityList);
    }

    public ResourceDTO getByUuidResource(String resourceUuid) throws IOException {
        Optional<ResourceEntity> resourceEntity = repository.findByUuid(resourceUuid);
        if (resourceEntity.isEmpty()) {
            System.out.println("There is no Resource with UUID: " + resourceUuid);
            throw new ResourceNotFoundException(resourceUuid);
        }

        return entityToDTO(resourceEntity.get());
    }

    public long deleteByUuidResource(String resourceUuid) {
        long result = repository.deleteByUuid(resourceUuid);
        if (result != 1L) {
            System.out.println("Error trying to delete Resource with " + resourceUuid);
            throw new ResourceNotFoundException(resourceUuid);
        }

        return result;
    }

    private List<ResourceDTO> mapResourceEntityToDTO(List<ResourceEntity> resourceEntityList) {
        List<ResourceDTO> resourceDTOList = new ArrayList<>();

        for (ResourceEntity resourceModel : resourceEntityList) {
            ResourceClassDTO resourceClassDTO = entityToClassDTO(resourceModel.getResourceClassEntity());
            ResourceDTO resourceObjects = entityToDTO(resourceModel);
            resourceObjects.setResourceClassDTO(resourceClassDTO);
            resourceDTOList.add(resourceObjects);
        }

        return resourceDTOList;
    }

    private ResourceClassDTO entityToClassDTO(ResourceClassEntity resourceClassEntity) {
        ResourceClassDTO resourceClassObject = new ResourceClassDTO();
        resourceClassObject.setName(resourceClassEntity.getName());
        resourceClassObject.setUuid(resourceClassEntity.getUuid());

        return resourceClassObject;
    }

    private ResourceDTO entityToDTO(ResourceEntity resourceEntity) {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setName(resourceEntity.getName());
        resourceDTO.setUuid(resourceEntity.getUuid());
        return resourceDTO;
    }
}
