package com.resourceservice.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.resourceservice.controller.ResourceController;
import com.resourceservice.model.CreateResourceClassDTO;
import com.resourceservice.model.CreateResourceDTO;
import com.resourceservice.model.ResourceClassDTO;
import com.resourceservice.model.ResourceClassEntity;
import com.resourceservice.model.ResourceDTO;
import com.resourceservice.model.ResourceEntity;
import com.resourceservice.model.UpdateResourceClassDTO;
import com.resourceservice.model.UpdateResourceDTO;
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
    final String classUuid = "75028399-b23c-4c08-a509-cb531c15286b";


    @Test
    public void createResourceObject_Success() throws Exception {
        ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
        resourceClassDTO.setUuid(classUuid);
        resourceClassDTO.setName("Dell");

        CreateResourceDTO createResourceDTO = new CreateResourceDTO.CreateResourceDTOBuilder()
                .withName("Mark")
                .withDescription("Laptop")
                .withResourceClassUuid(classUuid)
                .build();

        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setUuid(uuid);
        resourceDTO.setName("Mark");
        resourceDTO.setResourceClassDTO(resourceClassDTO);

        when(resourceService.createResource(any())).thenReturn(resourceDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/resource")
                                .content(asJsonString(createResourceDTO))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(resourceDTO)));

    }

    @Test
    public void getAllResourceObjects_Success() throws Exception {
        ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
        resourceClassDTO.setUuid(classUuid);
        resourceClassDTO.setName("Dell");

        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setUuid(uuid);
        resourceDTO.setName("Mark");
        resourceDTO.setResourceClassDTO(resourceClassDTO);

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
        ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
        resourceClassDTO.setUuid(classUuid);
        resourceClassDTO.setName("Dell");

        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setUuid(uuid);
        resourceDTO.setName("Mark");
        resourceDTO.setResourceClassDTO(resourceClassDTO);

        when(resourceService.getByUuidResource(any())).thenReturn(resourceDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/resource/{uuid}", uuid))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(resourceDTO)));
    }

    @Test
    public void updateResourceObject_Success() throws Exception {
        ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
        resourceClassDTO.setUuid(classUuid);
        resourceClassDTO.setName("Dell");

        UpdateResourceDTO updateResourceDTO = new UpdateResourceDTO.Builder()
                .withName("Dan")
                .build();

        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setUuid(uuid);
        resourceDTO.setName("Mark");
        resourceDTO.setResourceClassDTO(resourceClassDTO);

        when(resourceService.updateResource(any(), any())).thenReturn(resourceDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/resource/{uuid}", uuid)
                                .content(asJsonString(updateResourceDTO))
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
                .andExpect(MockMvcResultMatchers.content().string("Resource with uuid: " + uuid + " was Deleted"));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
