package com.resourceservice.service;

import com.resourceservice.model.CreateResourceDTO;
import com.resourceservice.model.ResourceClassDTO;
import com.resourceservice.model.ResourceClassEntity;
import com.resourceservice.model.ResourceDTO;
import com.resourceservice.model.ResourceEntity;
import com.resourceservice.model.UpdateResourceDTO;
import com.resourceservice.repository.ResourceClassRepository;
import com.resourceservice.repository.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceServiceTest {

    @InjectMocks
    private ResourceService classUnderTest;

    ResourceEntity resourceEntity = new ResourceEntity();
    ResourceClassEntity resourceClassEntity = new ResourceClassEntity();

    final String uuid = "65028399-b23c-4c08-a509-cb531c15286b";
    final String classUuid = "75028399-b23c-4c08-a509-cb531c15286b";

    @Mock
    private ResourceRepository resourceRepository;
    @Mock
    private ResourceClassRepository resourceClassRepository;


    @BeforeEach
    void init() {
        resourceClassEntity.setUuid(classUuid);
        resourceClassEntity.setName("Dell");
        resourceClassEntity.setId(1);

        resourceEntity.setUuid(uuid);
        resourceEntity.setName("Mark");
        resourceEntity.setResourceClassEntity(resourceClassEntity);
    }


    @Test
    void createResource_Success() throws IOException {
        List<ResourceClassEntity> listOfResourceClassEntities = new ArrayList<>();
        listOfResourceClassEntities.add(0, resourceClassEntity);

        ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
        resourceClassDTO.setName("Dell");
        resourceClassDTO.setUuid(classUuid);

        when(resourceClassRepository.findByUuid(any())).thenReturn(listOfResourceClassEntities);
        when(resourceRepository.save(any())).thenReturn(resourceEntity);

        ResourceDTO result = classUnderTest.createResource(
                new CreateResourceDTO.CreateResourceDTOBuilder()
                        .withName("Mark")
                        .withDescription("Laptop")
                        .withResourceClassDTO(resourceClassDTO)
                        .build()
        );

        assertThat(result).extracting("name", "uuid", "resourceClassDTO.uuid", "resourceClassDTO.name")
                .isNotNull()
                .isNotEmpty()
                .contains("Mark", uuid, resourceClassDTO.getUuid(), resourceClassDTO.getName());
    }

    @Test
    void createResource_Failure() throws IOException {
        List<ResourceClassEntity> listOfResourceClassEntities = new ArrayList<>();
        listOfResourceClassEntities.add(0, resourceClassEntity);

        ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
        resourceClassDTO.setName("Mac");
        resourceClassDTO.setUuid("2");

        when(resourceClassRepository.findByUuid(any())).thenReturn(listOfResourceClassEntities);
        when(resourceRepository.save(any())).thenReturn(resourceEntity);

        ResourceDTO result = classUnderTest.createResource(
                new CreateResourceDTO.CreateResourceDTOBuilder()
                        .withName("Mark")
                        .withDescription("Laptop")
                        .withResourceClassDTO(resourceClassDTO)
                        .build()
        );

        assertThat(result).extracting("name", "uuid", "resourceClassDTO.uuid", "resourceClassDTO.name")
                .isNotNull()
                .isNotEmpty()
                .doesNotContain("Roger", "1", resourceClassDTO.getUuid(), resourceClassDTO.getName());
    }

    @Test
    void updateResourceClass_Success() {
        List<ResourceEntity> listOfResourceEntities = new ArrayList<>();
        listOfResourceEntities.add(0, resourceEntity);

        when(resourceRepository.findByUuid(any())).thenReturn(listOfResourceEntities);
        when(resourceRepository.save(any())).thenReturn(resourceEntity);

        ResourceDTO result = classUnderTest.updateResource(
                new UpdateResourceDTO.Builder().withName("Dan").build(), uuid
        );
        assertThat(result).extracting("name", "uuid")
                .isNotNull()
                .isNotEmpty()
                .contains("Dan", uuid);

    }

    @Test
    void getAllResource_Success() {
        List<ResourceEntity> listOfResourceEntities = new ArrayList<>();
        listOfResourceEntities.add(0, resourceEntity);

        when(resourceRepository.findAll()).thenReturn(listOfResourceEntities);

        List<ResourceDTO> result = classUnderTest.getAllResource();
        assertThat(result).hasSize(1)
                .flatExtracting("name", "uuid")
                .isNotNull()
                .isNotEmpty()
                .contains("Mark", uuid);
    }

    @Test
    void getAllResource_Failure() {
        List<ResourceEntity> listOfResourceEntities = new ArrayList<>();
        listOfResourceEntities.add(0, resourceEntity);

        when(resourceRepository.findAll()).thenReturn(listOfResourceEntities);

        List<ResourceDTO> result = classUnderTest.getAllResource();
        assertThat(result).hasSize(1)
                .flatExtracting("name", "uuid")
                .isNotNull()
                .isNotEmpty()
                .doesNotContain("Dan", "1");
    }

    @Test
    void getByUuidResource_Success() throws IOException {
        List<ResourceEntity> listOfResourceEntities = new ArrayList<>();
        listOfResourceEntities.add(0, resourceEntity);

        when(resourceRepository.findByUuid(any())).thenReturn(listOfResourceEntities);

        ResourceDTO result = classUnderTest.getByUuidResource(uuid);
        assertThat(result).extracting("name", "uuid")
                .isNotNull()
                .isNotEmpty()
                .contains("Mark", uuid);
    }

    @Test
    void getByUuidResource_Failure() throws IOException {
        List<ResourceEntity> listOfResourceEntities = new ArrayList<>();
        listOfResourceEntities.add(0, resourceEntity);

        when(resourceRepository.findByUuid(any())).thenReturn(listOfResourceEntities);

        ResourceDTO result = classUnderTest.getByUuidResource(uuid);
        assertThat(result).extracting("name", "uuid")
                .isNotNull()
                .isNotEmpty()
                .doesNotContain("Dan", "1");
    }

    @Test
    void deleteByUuidResource_Success() {
        when(resourceRepository.deleteByUuid(any())).thenReturn(1L);

        long result = classUnderTest.deleteByUuidResource(uuid);
        assertThat(result).isEqualTo(1)
                .isNotNull();
    }

    @Test
    void deleteByUuidResource_Failure() {
        when(resourceRepository.deleteByUuid(any())).thenReturn(0L);

        long result = classUnderTest.deleteByUuidResource(uuid);
        assertThat(result).isEqualTo(0L)
                .isNotNull();
    }
}