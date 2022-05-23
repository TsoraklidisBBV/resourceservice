package com.resourceservice.model;

public class UpdateResourceClassDTO {
    String name;


    public String getName() {
        return name;
    }

    public static final class Builder {
        String name;

        public Builder() {
        }

        public static Builder anUpdateResourceClassDTO() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public UpdateResourceClassDTO build() {
            UpdateResourceClassDTO updateResourceClassDTO = new UpdateResourceClassDTO();
            updateResourceClassDTO.name = this.name;
            return updateResourceClassDTO;
        }
    }
}
