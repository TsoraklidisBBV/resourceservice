package com.resourceservice.service;

import com.resourceservice.model.ResourceClassDTO;
import com.resourceservice.model.ResourceClassEntity;
import com.resourceservice.repository.ResourceClassRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceClassServiceTest {

  ResourceClassService resourceClassService;
  ResourceClassEntity resourceClassEntity = new ResourceClassEntity();

  final String uuid = "65028399-b23c-4c08-a509-cb531c15286b";

  @Mock
  private ResourceClassRepository resourceClassRepository;

  @BeforeEach
  void init() {
    resourceClassService = new ResourceClassService(resourceClassRepository);

    resourceClassEntity.setUuid(uuid);
    resourceClassEntity.setName("Dell");
    resourceClassEntity.setId(1);
  }


  @Test
  void createResourceClass_Success() {
    ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
    resourceClassDTO.setName(resourceClassEntity.getName());
    resourceClassDTO.setUuid(resourceClassEntity.getUuid());

    when(resourceClassRepository.save(any())).thenReturn(resourceClassEntity);

    ResourceClassDTO result = resourceClassService.createResourceClass(new CreateResourceClassDTO());
    Assertions.assertEquals(resourceClassDTO.getName(), result.getName());
    Assertions.assertEquals(resourceClassDTO.getUuid(), result.getUuid());
  }


//    @Test
//    void updateResourceClass() {
//      ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
//      resourceClassDTO.setName(resourceClassEntity.getName());
//      resourceClassDTO.setUuid(resourceClassEntity.getUuid());
//
//      List<ResourceClassEntity> listOfResourceClassEntities = new ArrayList<>();
//      listOfResourceClassEntities.add(0, resourceClassEntity);
//
//      when(resourceClassRepository.findByUuid(any())).thenReturn(listOfResourceClassEntities);
//      when(resourceClassRepository.save(any())).thenReturn(resourceClassEntity);
//
//      //TODO: How to insert the update value
//      ResourceClassDTO result = resourceClassService.updateResourceClass(new UpdateResourceClassDTO(),uuid);
//      Assertions.assertEquals(resourceClassDTO.getName(), result.getName());
//      Assertions.assertEquals(resourceClassDTO.getUuid(), result.getUuid());
//
//    }

  @Test
  void getAllResourceClass_Success() {
    List<ResourceClassEntity> listOfResourceClassEntities = new ArrayList<>();
    listOfResourceClassEntities.add(0, resourceClassEntity);

    when(resourceClassRepository.findAll()).thenReturn(listOfResourceClassEntities);

    List<ResourceClassDTO> result = resourceClassService.getAllResourceClass();
    Assertions.assertEquals(1, result.size());
  }

  @Test
  void getAllResourceClass_Failure() {
    List<ResourceClassEntity> listOfResourceClassEntities = new ArrayList<>();
    listOfResourceClassEntities.add(0, resourceClassEntity);

    when(resourceClassRepository.findAll()).thenReturn(listOfResourceClassEntities);

    List<ResourceClassDTO> result = resourceClassService.getAllResourceClass();
    Assertions.assertNotEquals(2, result.size());
  }

  @Test
  void getByUuidResourceClass_Success() {
    List<ResourceClassEntity> listOfResourceClassEntities = new ArrayList<>();
    listOfResourceClassEntities.add(0, resourceClassEntity);

    when(resourceClassRepository.findByUuid(any())).thenReturn(listOfResourceClassEntities);

    ResourceClassDTO result = resourceClassService.getByUuidResourceClass(uuid);
    Assertions.assertEquals(resourceClassEntity.getUuid(), result.getUuid());
    Assertions.assertEquals(resourceClassEntity.getName(), result.getName());
  }

  @Test
  void getByUuidResourceClass_Failure() {
    List<ResourceClassEntity> listOfResourceClassEntities = new ArrayList<>();
    listOfResourceClassEntities.add(0, resourceClassEntity);

    when(resourceClassRepository.findByUuid(any())).thenReturn(listOfResourceClassEntities);

    ResourceClassDTO result = resourceClassService.getByUuidResourceClass(uuid);
    Assertions.assertNotEquals(1, result.getUuid());
    Assertions.assertNotEquals("Mac", result.getName());
  }

  @Test
  void deleteByUuidResourceClass_Success() {
    when(resourceClassRepository.deleteByUuid(any())).thenReturn(1L);

    long result = resourceClassService.deleteByUuidResourceClass(uuid);
    Assertions.assertEquals(1, result);
  }

  @Test
  void deleteByUuidResourceClass_Failure() {
    when(resourceClassRepository.deleteByUuid(any())).thenReturn(0L);

    long result = resourceClassService.deleteByUuidResourceClass(uuid);
    Assertions.assertNotEquals(1, result);
  }
}