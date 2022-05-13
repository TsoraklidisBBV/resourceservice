package com.resourceservice.service;

import com.resourceservice.model.ResourceClassDTO;
import com.resourceservice.model.ResourceClassEntity;
import com.resourceservice.model.UpdateResourceClassDTO;
import com.resourceservice.repository.ResourceClassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceClassServiceTest {

  @InjectMocks
  private ResourceClassService classUnderTest;
  ResourceClassEntity resourceClassEntity = new ResourceClassEntity();

  final String uuid = "65028399-b23c-4c08-a509-cb531c15286b";

  @Mock
  private ResourceClassRepository resourceClassRepository;

  @BeforeEach
  void init() {
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

    ResourceClassDTO result = classUnderTest.createResourceClass(
            new CreateResourceClassDTO.Builder().withName("Dell").build()
    );

    assertThat(result).extracting("name", "uuid")
            .isNotNull()
            .isNotEmpty()
            .contains("Dell", uuid);
  }

  @Test
  void updateResourceClass() {
    ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
    resourceClassDTO.setName(resourceClassEntity.getName());
    resourceClassDTO.setUuid(resourceClassEntity.getUuid());

    List<ResourceClassEntity> listOfResourceClassEntities = new ArrayList<>();
    listOfResourceClassEntities.add(0, resourceClassEntity);

    when(resourceClassRepository.findByUuid(any())).thenReturn(listOfResourceClassEntities);
    when(resourceClassRepository.save(any())).thenReturn(resourceClassEntity);

    ResourceClassDTO result = classUnderTest.updateResourceClass(
            new UpdateResourceClassDTO.Builder().withName("Mac").build(), uuid
    );
    assertThat(result).extracting("name", "uuid")
            .isNotNull()
            .isNotEmpty()
            .contains("Mac", uuid);

  }

  @Test
  void getAllResourceClass_Success() {
    List<ResourceClassEntity> listOfResourceClassEntities = new ArrayList<>();
    listOfResourceClassEntities.add(0, resourceClassEntity);

    when(resourceClassRepository.findAll()).thenReturn(listOfResourceClassEntities);

    List<ResourceClassDTO> result = classUnderTest.getAllResourceClass();
    assertThat(result).hasSize(1)
            .flatExtracting("name", "uuid")
            .isNotNull()
            .isNotEmpty()
            .contains("Dell", uuid);
  }

  @Test
  void getAllResourceClass_Failure() {
    List<ResourceClassEntity> listOfResourceClassEntities = new ArrayList<>();
    listOfResourceClassEntities.add(0, resourceClassEntity);

    when(resourceClassRepository.findAll()).thenReturn(listOfResourceClassEntities);

    List<ResourceClassDTO> result = classUnderTest.getAllResourceClass();
    assertThat(result).hasSize(1)
            .flatExtracting("name", "uuid")
            .isNotNull()
            .isNotEmpty()
            .doesNotContain("Mac", "1");
  }

  @Test
  void getByUuidResourceClass_Success() {
    List<ResourceClassEntity> listOfResourceClassEntities = new ArrayList<>();
    listOfResourceClassEntities.add(0, resourceClassEntity);

    when(resourceClassRepository.findByUuid(any())).thenReturn(listOfResourceClassEntities);

    ResourceClassDTO result = classUnderTest.getByUuidResourceClass(uuid);
    assertThat(result).extracting("name", "uuid")
            .isNotNull()
            .isNotEmpty()
            .contains("Dell", uuid);
  }

  @Test
  void getByUuidResourceClass_Failure() {
    List<ResourceClassEntity> listOfResourceClassEntities = new ArrayList<>();
    listOfResourceClassEntities.add(0, resourceClassEntity);

    when(resourceClassRepository.findByUuid(any())).thenReturn(listOfResourceClassEntities);

    ResourceClassDTO result = classUnderTest.getByUuidResourceClass(uuid);
    assertThat(result).extracting("name", "uuid")
            .isNotNull()
            .isNotEmpty()
            .doesNotContain("Mac", "1");
  }

  @Test
  void deleteByUuidResourceClass_Success() {
    when(resourceClassRepository.deleteByUuid(any())).thenReturn(1L);

    long result = classUnderTest.deleteByUuidResourceClass(uuid);
    assertThat(result).isEqualTo(1)
            .isNotNull();
  }

  @Test
  void deleteByUuidResourceClass_Failure() {
    when(resourceClassRepository.deleteByUuid(any())).thenReturn(0L);

    long result = classUnderTest.deleteByUuidResourceClass(uuid);
    assertThat(result).isEqualTo(0L)
            .isNotNull();
  }
}