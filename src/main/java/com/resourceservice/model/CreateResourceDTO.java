package com.resourceservice.model;

public class CreateResourceDTO {

    String name;
    String description;
    ResourceClassDTO resourceClassDTO;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ResourceClassDTO getResourceClassDTO() {
        return resourceClassDTO;
    }

    public static final class CreateResourceDTOBuilder {
        String name;
        String description;
        ResourceClassDTO resourceClassDTO;

        public CreateResourceDTOBuilder() {
        }

        public static CreateResourceDTOBuilder aCreateResourceDTO() {
            return new CreateResourceDTOBuilder();
        }

        public CreateResourceDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CreateResourceDTOBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public CreateResourceDTOBuilder withResourceClassDTO(ResourceClassDTO resourceClassDTO) {
            this.resourceClassDTO = resourceClassDTO;
            return this;
        }

        public CreateResourceDTO build() {
            CreateResourceDTO createResourceDTO = new CreateResourceDTO();
            createResourceDTO.name = this.name;
            createResourceDTO.resourceClassDTO = this.resourceClassDTO;
            createResourceDTO.description = this.description;
            return createResourceDTO;
        }
    }
}
