package com.resourceservice.controller;

import com.resourceservice.model.ResourceEntity;
import com.resourceservice.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/request/{resourceUuid}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getResourceByResourceUuidRest(@PathVariable(value = "resourceUuid") String resourceUuid) throws IOException {
        ResourceEntity resource = resourceService.getResourcesByName(resourceUuid);

        if (resource == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + resourceUuid)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/remove/{resourceUuid}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void deleteResourceByResourceUuidRest(@PathVariable(value = "resourceUuid") String resourceName) throws IOException {
//        ResourceModel resource = resourceService.getResourcesByName(resourceName);
//
//        if (resource == null) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }

        resourceService.deleteResourcesByName(resourceName);

//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body("Item deleted");
    }
}
