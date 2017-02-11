package org.fmunozn.addresses.alliescomputing.controller.mock;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
public class EircodeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAddressBasicTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pcw/test/address/ie/D02X285?lines=3&format=json").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[0].addressline1", is("Dept of Communications, Climate Change and Natural Resources")))
                .andExpect(jsonPath("$.[0].summaryline", is("Dept of Communications, Climate Change and Natural Resources, 29-31 Adelaide Road, Dublin 2, D02 X285")))
                .andExpect(jsonPath("$.[0].postcode", is("D02 X285")));                
                
    }
    
    @Test
    public void getAddressWithGeoTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pcw/test/addressgeo/ie/Adelaide Road?lines=3&format=json").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[0].addressline1", is("Dept of Communications, Climate Change and Natural Resources")))
                .andExpect(jsonPath("$.[0].summaryline", is("Dept of Communications, Climate Change and Natural Resources, 29-31 Adelaide Road, Dublin 2, D02 X285")))
                .andExpect(jsonPath("$.[0].postcode", is("D02 X285")))
                .andExpect(jsonPath("$.[0].latitude", is("53.332067")))
                .andExpect(jsonPath("$.[0].longitude", is("-6.255492")))
                ;                
                
    }
    
    @Test
    public void getAddressWithGeoAndW3WTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pcw/test/addressgeo/ie/Adelaide Road?lines=3&format=json&addtags=w3w").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[0].addressline1", is("Dept of Communications, Climate Change and Natural Resources")))
                .andExpect(jsonPath("$.[0].summaryline", is("Dept of Communications, Climate Change and Natural Resources, 29-31 Adelaide Road, Dublin 2, D02 X285")))
                .andExpect(jsonPath("$.[0].postcode", is("D02 X285")))
                .andExpect(jsonPath("$.[0].latitude", is("53.332067")))
                .andExpect(jsonPath("$.[0].longitude", is("-6.255492")))
                .andExpect(jsonPath("$.[0].what3words", is("lease.wiped.life")))
                ;                
                
    }
    
    @Test
    public void getCoordinatesTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pcw/test/rgeo/ie/53.332067/-6.255492?distance=50&format=json").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(19)))
                ;                
                
    }
}