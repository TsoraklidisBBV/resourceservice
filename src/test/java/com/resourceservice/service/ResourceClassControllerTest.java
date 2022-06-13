package com.resourceservice.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.resourceservice.controller.ResourceClassController;
import com.resourceservice.model.CreateResourceClassDTO;
import com.resourceservice.model.ResourceClassDTO;
import com.resourceservice.model.UpdateResourceClassDTO;
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

@WebMvcTest({ResourceClassController.class})
public class ResourceClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ResourceClassService resourceClassService;

    final String uuid = "65028399-b23c-4c08-a509-cb531c15286b";


    @Test
    public void createResourceObject_Success() throws Exception {
        CreateResourceClassDTO createResourceClassDTO = new CreateResourceClassDTO.Builder().withName("Dell").build();

        ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
        resourceClassDTO.setUuid(uuid);
        resourceClassDTO.setName("Dell");

        when(resourceClassService.createResourceClass(any())).thenReturn(resourceClassDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/resourceclass")
                                .content(asJsonString(createResourceClassDTO))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(resourceClassDTO)));

    }

    @Test
    public void getAllResourceObjects_Success() throws Exception {
        ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
        resourceClassDTO.setUuid(uuid);
        resourceClassDTO.setName("Dell");

        List<ResourceClassDTO> listOfResourceClassEntities = new ArrayList<>();
        listOfResourceClassEntities.add(0, resourceClassDTO);

        when(resourceClassService.getAllResourceClass()).thenReturn(listOfResourceClassEntities);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/retrieveallresourceclass"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json("[{}]"));
    }

    @Test
    public void getByUuidResourceObject_Success() throws Exception {
        ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
        resourceClassDTO.setUuid(uuid);
        resourceClassDTO.setName("Dell");

        when(resourceClassService.getByUuidResourceClass(any())).thenReturn(resourceClassDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/resourceclass/{uuid}", "1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(resourceClassDTO)));
    }

    @Test
    public void updateResourceObject_Success() throws Exception {
        UpdateResourceClassDTO updateResourceClassDTO = new UpdateResourceClassDTO.Builder().withName("Mac").build();

        ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
        resourceClassDTO.setUuid(uuid);
        resourceClassDTO.setName("Dell");

        when(resourceClassService.updateResourceClass(any(), any())).thenReturn(resourceClassDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/resourceclass/{uuid}", uuid)
                                .content(asJsonString(updateResourceClassDTO))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(resourceClassDTO)));
    }

    @Test
    public void deleteByUuidResourceObject_Success() throws Exception {
        when(resourceClassService.deleteByUuidResourceClass(any())).thenReturn(1L);

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/resourceclass/{uuid}", uuid))
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
