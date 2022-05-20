package com.resourceservice.service;

import com.resourceservice.model.ResourceEntity;
import com.resourceservice.repository.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class ResourceRepositoryTest {

    final String uuid = "65028399-b23c-4c08-a509-cb531c15286b";

    @Autowired
    private ResourceRepository classUnderTest;

    @BeforeEach
    void init() {
        ResourceEntity resourceEntity = new ResourceEntity();
        resourceEntity.setUuid(uuid);
        resourceEntity.setName("Mac");

        List<ResourceEntity> listOfResourceEntities = new ArrayList<>();
        listOfResourceEntities.add(0, resourceEntity);

        classUnderTest.saveAll(listOfResourceEntities);
    }

    @Test
    void save_Success() {
        ResourceEntity resourceEntity = new ResourceEntity();
        resourceEntity.setUuid(uuid);
        resourceEntity.setName("Dell");

        ResourceEntity result = classUnderTest.save(resourceEntity);
        assertThat(result).extracting("name", "uuid")
                .isNotNull()
                .isNotEmpty()
                .contains(resourceEntity.getName(), resourceEntity.getUuid());
    }

    @Test
    void findAll_Success() {
        List<ResourceEntity> result = classUnderTest.findAll();
        assertThat(result).hasSize(1)
                .flatExtracting("name", "uuid")
                .isNotNull()
                .isNotEmpty()
                .contains("Mac", uuid);
    }

    @Test
    void findByUuid_Success() {
        List<ResourceEntity> result = classUnderTest.findByUuid(uuid);
        assertThat(result).hasSize(1)
                .flatExtracting("name", "uuid")
                .isNotNull()
                .isNotEmpty()
                .contains("Mac", uuid);
    }

    @Test
    void findByUuid_Failure() {
        List<ResourceEntity> result = classUnderTest.findByUuid("2");
        assertThat(result).hasSize(0);
    }

    @Test
    void deleteByUuid_Success() {
        Long result = classUnderTest.deleteByUuid(uuid);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void deleteByUuid_Failure() {
        Long result = classUnderTest.deleteByUuid("2");
        assertThat(result).isEqualTo(0);
    }
}
