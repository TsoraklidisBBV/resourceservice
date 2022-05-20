package com.resourceservice.controller;

import com.resourceservice.model.CreateResourceDTO;
import com.resourceservice.model.ResourceDTO;
import com.resourceservice.model.ResourceEntity;
import com.resourceservice.model.UpdateResourceDTO;
import com.resourceservice.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
public class ResourceController {

    @Autowired
    private final ResourceService resourceService;

    public ResourceController(ResourceService mockApplicationImpl) {
        super();
        this.resourceService = mockApplicationImpl;
    }

    @PostMapping(path = "/resource",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResourceDTO> createResourceObject(@RequestBody CreateResourceDTO createResourceDTO) {
        ResourceDTO resourceDTO = resourceService.createResource(createResourceDTO);
        var location = URI.create(String.format("/resource/%s", resourceDTO.getUuid()));
        return ResponseEntity.created(location).body(resourceDTO);
    }

    @GetMapping(path = "retrieveallresource",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ResourceDTO>> getAllResourceObject() {
        List<ResourceDTO> resourceDTOList = resourceService.getAllResource();
        return ResponseEntity.ok().body(resourceDTOList);
    }

    @GetMapping(
            value = "/resource/{resourceUuid}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResourceDTO> getByUuidResourceObject(@PathVariable(value = "resourceUuid") String resourceUuid) throws IOException {
        ResourceDTO resourceDTO = resourceService.getByUuidResource(resourceUuid);

        if (resourceDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + resourceUuid)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resourceDTO);
    }

    @PutMapping(path = "/resource/{uuid}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResourceDTO> updateResourceObject(
            @PathVariable(value = "uuid") String uuid,
            @RequestBody UpdateResourceDTO updateResourceDTO) {
        ResourceDTO resourceDTO = resourceService.updateResource(updateResourceDTO, uuid);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(resourceDTO);
    }


    @DeleteMapping(
            value = "/resource/{uuid}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity deleteResourceByResourceUuid(@PathVariable(value = "uuid") String uuid) {
        resourceService.deleteByUuidResource(uuid);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Resource with uuid: " + uuid + " was Deleted");
    }
}
