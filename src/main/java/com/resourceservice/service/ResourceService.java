package com.resourceservice.service;

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

        List<ResourceClassEntity> resourceClassEntityList = resourceClassRepository.findByUuid(createResourceDTO.getResourceClassDTO().getUuid());
        //TODO: do a ResourceClasNotFoundException in the ControllerExceptionHnadler
        //        if (resourceClassEntityList.isEmpty()) {
//            System.out.println("There is no Resource Class to return");
//            return null;
//        }

        resourceEntity.setResourceClassEntity(resourceClassEntityList.get(0));
        ResourceEntity savedResourceEntity = repository.save(resourceEntity);
        ResourceClassDTO resourceClassDTO = entityToClassDTO(resourceEntity);
        return entityToDTO(savedResourceEntity, resourceClassDTO);
    }

    public ResourceDTO updateResource(UpdateResourceDTO updateResourceDTO, String uuid) {
        ResourceEntity resourceEntity = repository.findByUuid(uuid).get(0);
        resourceEntity.setName(updateResourceDTO.getName());
        ResourceEntity savedResourceEntity = repository.save(resourceEntity);
        ResourceClassDTO resourceClassDTO = entityToClassDTO(resourceEntity);
        return entityToDTO(savedResourceEntity, resourceClassDTO);
    }

    public List<ResourceDTO> getAllResource() {
        List<ResourceEntity> resourceEntityList = repository.findAll();
        return mapResourceEntityToDTO(resourceEntityList);
    }

    public ResourceDTO getByUuidResource(String resourceUuid) throws IOException {
        List<ResourceEntity> resourceEntityList = repository.findByUuid(resourceUuid);
        return mapResourceEntityToDTO(resourceEntityList).get(0);
    }

    public long deleteByUuidResource(String resourceUuid) {
        return repository.deleteByUuid(resourceUuid);
    }

    private List<ResourceDTO> mapResourceEntityToDTO(List<ResourceEntity> resourceEntityList) {
        if (resourceEntityList.isEmpty()) {
            System.out.println("There is no Resource to return");
            return null;
        }

        List<ResourceDTO> resourceDTOList = new ArrayList<>();

        for (ResourceEntity resourceModel : resourceEntityList) {
            ResourceClassDTO resourceClassDTO = entityToClassDTO(resourceModel);
            ResourceDTO resourceObjects = entityToDTO(resourceModel, resourceClassDTO);
            resourceDTOList.add(resourceObjects);
        }

        return resourceDTOList;
    }

    private ResourceClassDTO entityToClassDTO(ResourceEntity resourceEntity) {
        ResourceClassDTO resourceClassObject = new ResourceClassDTO();
        resourceClassObject.setName(resourceEntity.getResourceClassEntity().getName());
        resourceClassObject.setUuid(resourceEntity.getResourceClassEntity().getUuid());

        return resourceClassObject;
    }

    private ResourceDTO entityToDTO(ResourceEntity resourceEntity, ResourceClassDTO resourceClassDTO) {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setName(resourceEntity.getName());
        resourceDTO.setUuid(resourceEntity.getUuid());
        resourceDTO.setResourceClassDTO(resourceClassDTO);
        return resourceDTO;
    }
}
