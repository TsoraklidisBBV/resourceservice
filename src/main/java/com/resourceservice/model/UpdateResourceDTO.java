package com.resourceservice.model;

public class UpdateResourceDTO {

    String name;

    //String uuid for ResourceEntity search


    public String getName() {
        return name;
    }

    public static final class Builder {
        String name;

        public Builder() {
        }

        public static UpdateResourceDTO.Builder anUpdateResourceDTO() {
            return new UpdateResourceDTO.Builder();
        }

        public UpdateResourceDTO.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public UpdateResourceDTO build() {
            UpdateResourceDTO updateResourceDTO = new UpdateResourceDTO();
            updateResourceDTO.name = this.name;
            return updateResourceDTO;
        }
    }


}
