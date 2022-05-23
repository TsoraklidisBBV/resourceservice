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

        //  ResourceDTO returnFromGet = getByUuidResource(createResourceDTO.getResourceClassEntity().getUuid());

        //use uuid to get the ResourceClassEntity
        List<ResourceClassEntity> resourceClassEntityList = resourceClassRepository.findByUuid(createResourceDTO.getResourceClassDTO().getUuid());
        if (resourceClassEntityList.isEmpty()) {
            System.out.println("There is no Resource Class to return");
            return null;
        }

        resourceEntity.setResourceClassEntity(resourceClassEntityList.get(0));
        return saveResourceEntity(resourceEntity);
    }

    public ResourceDTO updateResource(UpdateResourceDTO updateResourceDTO, String uuid) {
        ResourceEntity resourceEntity = repository.findByUuid(uuid).get(0);
        resourceEntity.setName(updateResourceDTO.getName());
        return saveResourceEntity(resourceEntity);
    }

    private ResourceDTO saveResourceEntity(ResourceEntity resourceEntity) {
        ResourceEntity savedResourceEntity = repository.save(resourceEntity);
        ResourceClassDTO resourceClassObject = new ResourceClassDTO();
        resourceClassObject.setName(resourceEntity.getResourceClassEntity().getName());
        resourceClassObject.setUuid(resourceEntity.getResourceClassEntity().getUuid());

        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setName(savedResourceEntity.getName());
        resourceDTO.setUuid(savedResourceEntity.getUuid());
        resourceDTO.setResourceClassDTO(resourceClassObject);
        return resourceDTO;
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
            ResourceDTO resourceObjects = new ResourceDTO();
            ResourceClassDTO resourceClassObjects = new ResourceClassDTO();
            resourceClassObjects.setName(resourceModel.getResourceClassEntity().getName());
            resourceClassObjects.setUuid(resourceModel.getResourceClassEntity().getUuid());

            resourceObjects.setName(resourceModel.getName());
            resourceObjects.setUuid(resourceModel.getUuid());
            resourceObjects.setResourceClassDTO(resourceClassObjects);
            resourceDTOList.add(resourceObjects);
        }

        return resourceDTOList;
    }
}
