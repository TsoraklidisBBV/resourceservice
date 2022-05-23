package com.resourceservice.service;

import com.resourceservice.model.CreateResourceDTO;
import com.resourceservice.model.ResourceClassDTO;
import com.resourceservice.model.ResourceClassEntity;
import com.resourceservice.model.ResourceDTO;
import com.resourceservice.model.ResourceEntity;
import com.resourceservice.model.UpdateResourceDTO;
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

    public ResourceDTO createResource(CreateResourceDTO createResourceDTO) {
        ResourceEntity result = new ResourceEntity();
        result.setName(createResourceDTO.getName());
        result.setUuid(UUID.randomUUID().toString());
        result.setDescription(createResourceDTO.getDescription());
        //use uuid to get the ResourceClassEntity
        ResourceEntity savedResourceEntity = repository.save(result);

        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setName(savedResourceEntity.getName());
        resourceDTO.setUuid(savedResourceEntity.getUuid());
        return resourceDTO;
    }

    public ResourceDTO updateResource(UpdateResourceDTO updateResourceDTO, String uuid) {
        ResourceEntity resourceEntity = repository.findByUuid(uuid).get(0);
        resourceEntity.setName(updateResourceDTO.getName());
        ResourceEntity savedResourceEntity = repository.save(resourceEntity);

        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setName(savedResourceEntity.getName());
        resourceDTO.setUuid(uuid);
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

    private ResourceEntity findResourceByUuid(List<ResourceEntity> modelList, String resourceUuid) {
        if (modelList.isEmpty()) {
            System.out.println("Wrong uuid");
            return null;
        }

        return modelList.get(0);
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
