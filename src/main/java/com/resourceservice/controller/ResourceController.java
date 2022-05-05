package com.resourceservice.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/request/{resourceName}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity getResourceRest(@PathVariable(value = "resourceName") String resourceName) {

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + resourceName)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{hey}");
    }
}
