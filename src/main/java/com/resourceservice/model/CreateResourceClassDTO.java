package com.resourceservice.model;

public class CreateResourceClassDTO {


    public String getName() {
        return name;
    }

    String name;

    public static final class Builder {
        String name;

        public Builder() {
        }

        public static Builder CreateResourceClassDTO() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder but() {
            return CreateResourceClassDTO().withName(name);
        }

        public CreateResourceClassDTO build() {
            CreateResourceClassDTO createResourceClassDTO = new CreateResourceClassDTO();
            createResourceClassDTO.name = this.name;
            return createResourceClassDTO;
        }
    }
}
