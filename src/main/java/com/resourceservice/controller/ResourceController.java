package com.resourceservice.controller;

import com.resourceservice.model.ResourceEntity;
import com.resourceservice.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ResourceController {

    @Autowired
    private final ResourceService resourceService;

    public ResourceController(ResourceService mockApplicationImpl) {
        super();
        this.resourceService = mockApplicationImpl;
    }

//    @PostMapping(path = "/createresourse",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ResourceEntity> createResourceObject(@RequestBody CreateResourceDTO createResourceDTO) {
//        ResourceEntity resourceEntity = resourceService.createResource(createResourceDTO);
//        var location = URI.create(String.format("/createresourse/%s", resourceEntity.getUuid()));
//        return ResponseEntity.created(location).body(resourceEntity);
//    }

// @PutMapping

    @GetMapping(
            value = "/request/{resourceUuid}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getResourceByResourceUuid(@PathVariable(value = "resourceUuid") String resourceUuid) throws IOException {
        ResourceEntity resource = resourceService.getResourcesByUuid(resourceUuid);

        if (resource == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + resourceUuid)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource);
    }

    @DeleteMapping(
            value = "/removeresource/{resourceUuid}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity deleteResourceByResourceUuid(@PathVariable(value = "resourceUuid") String resourceUuid) {
        resourceService.deleteResourcesByUuid(resourceUuid);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Item deleted");
    }
}
