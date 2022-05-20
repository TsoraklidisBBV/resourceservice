package com.resourceservice.service;

import com.resourceservice.model.ResourceClassEntity;
import com.resourceservice.repository.ResourceClassRepository;
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
class ResourceClassRepositoryTest {

    final String uuid = "65028399-b23c-4c08-a509-cb531c15286b";

    @Autowired
    private ResourceClassRepository classUnderTest;

    @BeforeEach
    void init() {
        ResourceClassEntity resourceClassEntity = new ResourceClassEntity();
        resourceClassEntity.setUuid(uuid);
        resourceClassEntity.setName("Mac");
        resourceClassEntity.setId(1);

        List<ResourceClassEntity> listOfResourceClassEntities = new ArrayList<>();
        listOfResourceClassEntities.add(0, resourceClassEntity);

        classUnderTest.saveAll(listOfResourceClassEntities);
    }

    @Test
    void save_Success() {
        ResourceClassEntity resourceClassEntity = new ResourceClassEntity();
        resourceClassEntity.setUuid(uuid);
        resourceClassEntity.setName("Dell");
        resourceClassEntity.setId(1);

        ResourceClassEntity result = classUnderTest.save(resourceClassEntity);
        assertThat(result).extracting("name", "uuid")
                .isNotNull()
                .isNotEmpty()
                .contains(resourceClassEntity.getName(), resourceClassEntity.getUuid());
    }

    @Test
    void findAll_Success() {
        List<ResourceClassEntity> result = classUnderTest.findAll();
        assertThat(result).hasSize(1)
                .flatExtracting("name", "uuid")
                .isNotNull()
                .isNotEmpty()
                .contains("Mac", uuid);
    }

    @Test
    void findByUuid_Success() {
        List<ResourceClassEntity> result = classUnderTest.findByUuid(uuid);
        assertThat(result).hasSize(1)
                .flatExtracting("name", "uuid")
                .isNotNull()
                .isNotEmpty()
                .contains("Mac", uuid);
    }

    @Test
    void findByUuid_Failure() {
        List<ResourceClassEntity> result = classUnderTest.findByUuid("2");
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
