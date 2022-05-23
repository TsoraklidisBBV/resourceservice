package com.resourceservice.model;

public class ResourceDTO {

    String name;
    String uuid;
    ResourceClassDTO resourceClassDTO;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ResourceClassDTO getResourceClassDTO() {
        return resourceClassDTO;
    }

    public void setResourceClassDTO(ResourceClassDTO resourceClassDTO) {
        this.resourceClassDTO = resourceClassDTO;
    }
}
