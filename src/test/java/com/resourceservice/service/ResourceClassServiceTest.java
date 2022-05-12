package com.resourceservice.service;

import com.resourceservice.model.ResourceClassDTO;
import com.resourceservice.model.ResourceClassEntity;
import com.resourceservice.repository.ResourceClassRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceClassServiceTest {

    ResourceClassService resourceClassService;

    @Mock
    private ResourceClassRepository resourceClassRepository;

    @BeforeEach
    void init() {
        resourceClassService = new ResourceClassService();

        ResourceClassEntity resourceClassEntity = new ResourceClassEntity();
        resourceClassEntity.setUuid("1");
        resourceClassEntity.setName("Red");
        resourceClassEntity.setId(1);

        List<ResourceClassEntity> listOfResourceClassEntities = new ArrayList<>();
        listOfResourceClassEntities.add(0, resourceClassEntity);

        resourceClassRepository.saveAll(listOfResourceClassEntities);
    }

    @Test
    void createResourceClass() {
    }

    @Test
    void updateResourceClass() {
    }

    @Test
    void getAllResourceClass() {
        ResourceClassEntity resourceClassEntity = new ResourceClassEntity();
        resourceClassEntity.setUuid("1");
        resourceClassEntity.setName("Red");
        resourceClassEntity.setId(1);

        List<ResourceClassEntity> listOfResourceClassEntities = new ArrayList<>();
        listOfResourceClassEntities.add(0, resourceClassEntity);

        when(resourceClassRepository.findAll()).thenReturn(listOfResourceClassEntities);

        List<ResourceClassDTO> mockList = resourceClassService.getAllResourceClass();
        Assertions.assertEquals(mockList.size(), 1);
    }

    @Test
    void getByUuidResourceClass() {
    }

    @Test
    void deleteByUuidResourceClass() {
    }
}