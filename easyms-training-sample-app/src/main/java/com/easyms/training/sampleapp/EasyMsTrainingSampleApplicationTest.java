package com.easyms.training.sampleapp;

import com.easyms.training.sampleapp.ClientResourceTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.easyms.training.sampleapp.model.dto.ClientDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class EasyMsTrainingSampleApplicationTest extends ClientResourceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void testGetAllEmployees() throws Exception {
        mockMvc.perform(get("/api/v1/clients"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[*].id").exists());

    }

    @Test
    public void testGetEmployeeById() throws Exception {
        mockMvc.perform(get("/api/v1/clients/{id}",3))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("Mohamed"));


    }

    @Test
    public void testCreateClient() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/v1/clients")
                .content(asJsonString(ClientDTO.builder().firstname("walid").lastname("test").email("tto@to.com")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").exists());
    }
    @Test
    public void updateClientTest() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .put("/api/v1/clients/{id}", 3)
                .content(asJsonString(ClientDTO.builder().id(3L).firstname("firstName2").lastname("lastName2").email("email2@mail.com")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("firstName2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastName2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email2@mail.com"));
    }

    @Test
    public void deleteClientTest() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .delete("api/v1/clients/{id}", 3) )
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    public void findsByFullName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("api/v1/clients/search")
                .param("firstname", "walid")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper map = new ObjectMapper();
            map.setVisibility(map.getVisibilityChecker()
                    .withFieldVisibility(JsonAutoDetect.Visibility.ANY));
            return map.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
