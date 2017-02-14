package org.fmunozn.addresses.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.fmunozn.addresses.configuration.AddressesApiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressesApiApplication.class)
@AutoConfigureMockMvc
public class HealthCheckControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/healthcheck/basic").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"message\":\"I'm alive: test\"}")));
    }
}