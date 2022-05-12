package com.resourceservice.service;

import com.resourceservice.model.ResourceClassEntity;
import com.resourceservice.repository.ResourceClassRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class ResourceClassRepositoryTest {

    @Autowired
    private ResourceClassRepository resourceClassRepository;

    @BeforeEach
    void init() {
        ResourceClassEntity resourceClassEntity = new ResourceClassEntity();
        resourceClassEntity.setUuid("1");
        resourceClassEntity.setName("Mac");
        resourceClassEntity.setId(1);

        List<ResourceClassEntity> listOfResourceClassEntities = new ArrayList<>();
        listOfResourceClassEntities.add(0, resourceClassEntity);

        resourceClassRepository.saveAll(listOfResourceClassEntities);
    }

    @Test
    void save_Success() {
        ResourceClassEntity resourceClassEntity = new ResourceClassEntity();
        resourceClassEntity.setUuid("1");
        resourceClassEntity.setName("Dell");
        resourceClassEntity.setId(1);

        ResourceClassEntity result = resourceClassRepository.save(resourceClassEntity);
        Assertions.assertEquals(resourceClassEntity.getName(), result.getName());
        Assertions.assertEquals(resourceClassEntity.getUuid(), result.getUuid());
    }

    @Test
    void findAll_Success() {
        List<ResourceClassEntity> mockList = resourceClassRepository.findAll();
        Assertions.assertEquals(1, mockList.size());
    }

    @Test
    void findByUuid_Success() {
        List<ResourceClassEntity> mockList = resourceClassRepository.findByUuid("1");
        Assertions.assertEquals(1, mockList.size());
    }

    @Test
    void findByUuid_Failure() {
        List<ResourceClassEntity> mockList = resourceClassRepository.findByUuid("2");
        Assertions.assertEquals(0, mockList.size());
    }

    @Test
    void deleteByUuid_Success() {
        Long numberOfDeletedEntities = resourceClassRepository.deleteByUuid("1");
        Assertions.assertEquals(1, numberOfDeletedEntities);
    }

    @Test
    void deleteByUuid_Failure() {
        Long numberOfDeletedEntities = resourceClassRepository.deleteByUuid("2");
        Assertions.assertEquals(0, numberOfDeletedEntities);
    }
}
