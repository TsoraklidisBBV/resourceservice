package com.resourceservice.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.resourceservice.controller.ResourceController;
import com.resourceservice.model.ResourceClassEntity;
import com.resourceservice.model.ResourceDTO;
import com.resourceservice.model.ResourceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ResourceController.class})
public class ResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ResourceService resourceService;

    final String uuid = "65028399-b23c-4c08-a509-cb531c15286b";


    @Test
    public void createResourceObject_Success() throws Exception {
        ResourceClassEntity resourceClassEntity = new ResourceClassEntity();
        resourceClassEntity.setUuid(uuid);
        resourceClassEntity.setName("Mac");
        resourceClassEntity.setId(1);

        ResourceEntity resourceEntity = new ResourceEntity(
                1,
                "Dan",
                uuid,
                "red",
                resourceClassEntity
        );

        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setUuid(uuid);
        resourceDTO.setName("Dell");

        when(resourceService.createResource(any())).thenReturn(resourceDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/resource")
                                .content(asJsonString(resourceEntity))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(resourceDTO)));

    }

    @Test
    public void getAllResourceObjects_Success() throws Exception {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setUuid(uuid);
        resourceDTO.setName("Dell");

        List<ResourceDTO> listOfResourceEntities = new ArrayList<>();
        listOfResourceEntities.add(0, resourceDTO);


        when(resourceService.getAllResource()).thenReturn(listOfResourceEntities);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/retrieveallresource"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json("[{}]"));
    }

    @Test
    public void getByUuidResourceObject_Success() throws Exception {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setUuid(uuid);
        resourceDTO.setName("Dell");

        when(resourceService.getByUuidResource(any())).thenReturn(resourceDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/resource/{uuid}", uuid))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(resourceDTO)));
    }

    @Test
    public void updateResourceObject_Success() throws Exception {
        ResourceClassEntity resourceClassEntity = new ResourceClassEntity();
        resourceClassEntity.setUuid(uuid);
        resourceClassEntity.setName("Mac");
        resourceClassEntity.setId(1);

        ResourceEntity resourceEntity = new ResourceEntity(
                1,
                "Dan",
                uuid,
                "red",
                resourceClassEntity
        );

        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setUuid("1");
        resourceDTO.setName("Dell");

        when(resourceService.updateResource(any(), any())).thenReturn(resourceDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/resource/{uuid}", uuid)
                                .content(asJsonString(resourceEntity))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(resourceDTO)));
    }

    @Test
    public void deleteByUuidResourceObject_Success() throws Exception {
        when(resourceService.deleteByUuidResource(any())).thenReturn(1L);

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/resource/{uuid}", uuid))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("Resource with uuid: 1 was Deleted"));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
