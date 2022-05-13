package com.resourceservice.service;

public class CreateResourceClassDTO {


    public String getName() {
        return name;
    }

    String name;

    public static final class Builder {
        String name;

        Builder() {
        }

        public static Builder aCreateResourceClassDTO() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder but() {
            return aCreateResourceClassDTO().withName(name);
        }

        public CreateResourceClassDTO build() {
            CreateResourceClassDTO createResourceClassDTO = new CreateResourceClassDTO();
            createResourceClassDTO.name = this.name;
            return createResourceClassDTO;
        }
    }
}
