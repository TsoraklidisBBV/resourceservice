package com.resourceservice.model;

public class CreateResourceDTO {

    String name;
    String description;

    String resourceClassUuid;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getResourceClassUuid() {
        return resourceClassUuid;
    }


    public static final class CreateResourceDTOBuilder {
        String name;
        String description;
        String resourceClassUuid;

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

        public CreateResourceDTOBuilder withResourceClassUuid(String resourceClassUuid) {
            this.resourceClassUuid = resourceClassUuid;
            return this;
        }

        public CreateResourceDTO build() {
            CreateResourceDTO createResourceDTO = new CreateResourceDTO();
            createResourceDTO.description = this.description;
            createResourceDTO.name = this.name;
            createResourceDTO.resourceClassUuid = this.resourceClassUuid;
            return createResourceDTO;
        }
    }
}
