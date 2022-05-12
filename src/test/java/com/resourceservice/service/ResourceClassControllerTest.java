package com.resourceservice.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.resourceservice.controller.ResourceClassController;
import com.resourceservice.model.ResourceClassDTO;
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

@WebMvcTest({ResourceClassController.class})
public class ResourceClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ResourceClassService resourceClassService;

    @Test
    public void createResourceObject_Success() throws Exception {
        ResourceEntity resourceEntity = new ResourceEntity(
                1,
                "Dan",
                "Dell"
        );

        ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
        resourceClassDTO.setUuid("1");
        resourceClassDTO.setName("Dell");

        when(resourceClassService.createResourceClass(any())).thenReturn(resourceClassDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/resourceclass")
                                .content(asJsonString(resourceEntity))
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
        resourceClassDTO.setUuid("1");
        resourceClassDTO.setName("Dell");

        List<ResourceClassDTO> listOfResourceClassEntities = new ArrayList<>();
        listOfResourceClassEntities.add(0, resourceClassDTO);


        when(resourceClassService.getAllResourceClass()).thenReturn(listOfResourceClassEntities);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/retreiveallresourceclass"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json("[{}]"));
    }

    @Test
    public void getByUuidResourceObject_Success() throws Exception {
        ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
        resourceClassDTO.setUuid("1");
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
        ResourceEntity resourceEntity = new ResourceEntity(
                1,
                "Dan",
                "Dell"
        );

        ResourceClassDTO resourceClassDTO = new ResourceClassDTO();
        resourceClassDTO.setUuid("1");
        resourceClassDTO.setName("Dell");

        when(resourceClassService.updateResourceClass(any(), any())).thenReturn(resourceClassDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/resourceclass/{uuid}", "1")
                                .content(asJsonString(resourceEntity))
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
                        MockMvcRequestBuilders.delete("/resourceclass/{uuid}", "1"))
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
