package com.resourceservice.service;

import com.resourceservice.model.ResourceClassEntity;
import com.resourceservice.model.ResourceEntity;
import com.resourceservice.repository.ResourceClassRepository;
import com.resourceservice.repository.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class ResourceRepositoryTest {

    final String uuid = "85028399-b23c-4c08-a509-cb531c15286b";
    final String entityUuid = "75028399-b23c-4c08-a509-cb531c15286b";

    @Autowired
    private ResourceRepository classUnderTest;

    @Autowired
    private ResourceClassRepository resourceClassRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void init() {
        ResourceClassEntity resourceClassEntity = new ResourceClassEntity();
        resourceClassEntity.setUuid(uuid);
        resourceClassEntity.setName("Mac");
        resourceClassRepository.save(resourceClassEntity);

        ResourceEntity resourceEntity = new ResourceEntity();
        resourceEntity.setUuid(uuid);
        resourceEntity.setDescription("red");
        resourceEntity.setResourceClassEntity(resourceClassEntity);
        resourceEntity.setName("Mark");

        List<ResourceEntity> listOfResourceEntities = new ArrayList<>();
        listOfResourceEntities.add(0, resourceEntity);

        classUnderTest.saveAll(listOfResourceEntities);
        entityManager.flush();
    }

    @Test
    void save_Success() {
        ResourceClassEntity resourceClassEntity = new ResourceClassEntity();
        resourceClassEntity.setUuid("fdfdf");
        resourceClassEntity.setName("Mac");

        resourceClassRepository.save(resourceClassEntity);

        ResourceEntity resourceEntity = new ResourceEntity();
        resourceEntity.setUuid("redred");
        resourceEntity.setDescription("red");
        resourceEntity.setResourceClassEntity(resourceClassEntity);
        resourceEntity.setName("Jack");

        ResourceEntity result = classUnderTest.save(resourceEntity);
        assertThat(result).extracting("name", "uuid", "description", "resourceClassEntity")
                .isNotNull()
                .isNotEmpty()
                .contains(resourceEntity.getName(), resourceEntity.getUuid(), resourceEntity.getDescription(), resourceClassEntity);
    }

    @Test
    void findAll_Success() {
        List<ResourceEntity> result = classUnderTest.findAll();
        assertThat(result).hasSize(4)
                .flatExtracting("name", "uuid")
                .isNotNull()
                .isNotEmpty()
                .contains("Mark", uuid);
    }

    @Test
    void findByUuid_Success() {
        Optional<ResourceEntity> result = classUnderTest.findByUuid(uuid);
        assertThat(result).get().extracting("name", "uuid")
                .isNotNull()
                .isNotEmpty()
                .contains("Mark", uuid);
    }

    @Test
    void findByUuid_Failure() {
        Optional<ResourceEntity> result = classUnderTest.findByUuid("2");
        assertThat(result).isEmpty();
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
