package com.resourceservice.controller;

import com.resourceservice.model.ResourceModel;
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
            value = "/request/{userName}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getResourceByUserNameRest(@PathVariable(value = "userName") String resourceName) throws IOException {
        ResourceModel resource = resourceService.getResourcesByName(resourceName);

        if (resource == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + resourceName)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/remove/{userName}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void deleteResourceByUserNameRest(@PathVariable(value = "userName") String resourceName) throws IOException {
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
