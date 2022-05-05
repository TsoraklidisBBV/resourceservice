package com.resourceservice.controller;

import com.resourceservice.service.CreateResourceClassDTO;
import com.resourceservice.model.ResourceClassEntity;
import com.resourceservice.service.ResourceClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@Transactional
public class ResourceClassController {
    @Autowired
    private ResourceClassService resourceClassService;

    @PostMapping(path = "/resourceclass",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResourceClassEntity> createResourceObject(@RequestBody CreateResourceClassDTO createResourceClassDTO) {
        ResourceClassEntity resourceClassEntity = resourceClassService.createResourceClass(createResourceClassDTO);
        var location = URI.create(String.format("/resourceclass/%s", resourceClassEntity.getUuid()));
        return ResponseEntity.created(location).body(resourceClassEntity);
    }

    @GetMapping(path = "/retreiveallresourceclass",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResourceClassEntity>> getResourceObject() {
        List<ResourceClassEntity> resourceClassEntity = resourceClassService.getAllResourceClass();
        return ResponseEntity.ok().body(resourceClassEntity);
    }

    @GetMapping(path = "/resourceclass/{uuid}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResourceClassEntity> getByUuidResourceObject(@PathVariable(value = "uuid") String uuid) {
        ResourceClassEntity resourceClassEntity = resourceClassService.getByUuidResourceClass(uuid);

        if (resourceClassEntity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(resourceClassEntity);
    }

    @DeleteMapping(path = "/resourceclass/{uuid}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteByUuidResourceObject(@PathVariable(value = "uuid") String uuid) {
        resourceClassService.deleteByUuidResourceClass(uuid);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("Resource with uuid:" + uuid + "was Deleted");
    }
}
