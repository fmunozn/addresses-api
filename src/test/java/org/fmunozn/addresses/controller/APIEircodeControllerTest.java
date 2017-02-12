package org.fmunozn.addresses.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
@SpringBootTest(classes = AddressesApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class APIEircodeControllerTest {

    @Autowired
    private MockMvc mvc;

    // BASIC FUNCTIONALITY TESTS WITH MOCK DATA
    
    @Test
    public void getAddressBasicTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/address/ie/D02X285?lines=3&format=json").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[0].addressline1", is("Dept of Communications, Climate Change and Natural Resources")))
                .andExpect(jsonPath("$.[0].summaryline", is("Dept of Communications, Climate Change and Natural Resources, 29-31 Adelaide Road, Dublin 2, D02 X285")))
                .andExpect(jsonPath("$.[0].postcode", is("D02 X285")));                
                
    }
    
    @Test
    public void getAddressWithGeoTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/addressgeo/ie/Adelaide Road?lines=3&format=json").accept(MediaType.APPLICATION_JSON))
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
        mvc.perform(MockMvcRequestBuilders.get("/addressgeo/ie/Adelaide Road?lines=3&format=json&addtags=w3w").accept(MediaType.APPLICATION_JSON))
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
    public void getReverseCoordinatesTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/rgeo/ie/53.332067/-6.255492?distance=50&format=json").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(19)))
                ;                
                
    }
    
    // PARAMETER VALIDATION TESTS

    
    @Test
    public void getErrorAddressLookupShortFragment() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/address/ie/D02").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Empty or wrong fragment - it has to be at least 5 chars length"))
        ;
                
    }
     
    @Test
    public void getErrorAddressLookupWrongLinesParameterType() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/address/ie/D02X285?lines=text&format=json").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        ;                
    }
    
    @Test
    public void getErrorAddressLookupWrongLineNumber1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/address/ie/D02X285?lines=4&format=json").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Wrong number of additional lines - it has to be between 1 and 4"))
        ;                
    }
    
    @Test
    public void getErrorAddressLookupWrongLineNumber2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/address/ie/D02X285?lines=0&format=json").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Wrong number of additional lines - it has to be between 1 and 4"))
        ;                
    }
    
    @Test
    public void getErrorAddressLookupWrongPageNumber() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/address/ie/D02X285?page=-1&format=json").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Wrong page number - it has to be 0 or more"))
        ;                
    }
    
    @Test
    public void noErrorAddressLookupAdditionalUnknownParameter() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/address/ie/D02X285?lines=3&format=json&unknownparam=text").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        ;
    }
    
    @Test
    public void getErrorWithWrongLatitudeReverseCoordinatesTest1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/rgeo/ie/91/-6.255492?distance=50&format=json").accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isBadRequest())
                .andExpect(content().string("Wrong latitude parameter - it has to be between -90 and +90"))
                ;                
                
    }
    
    @Test
    public void getErrorWithWrongLatitudeReverseCoordinatesTest2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/rgeo/ie/-91/-6.255492?distance=50&format=json").accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isBadRequest())
                .andExpect(content().string("Wrong latitude parameter - it has to be between -90 and +90"))
                ;                
                
    }
    
    @Test
    public void getErrorWithWrongLongitudeReverseCoordinatesTest1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/rgeo/ie/54/-181?distance=50&format=json").accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isBadRequest())
                .andExpect(content().string("Wrong longitude parameter - it has to be between -180 and +180"))
                ;                
                
    }
    
    @Test
    public void getErrorWithWrongLongitudeReverseCoordinatesTest2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/rgeo/ie/54/181?distance=50&format=json").accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isBadRequest())
                .andExpect(content().string("Wrong longitude parameter - it has to be between -180 and +180"))
                ;                
                
    }
    
    @Test
    public void getErrorWithWrongDistanceReverseCoordinatesTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/rgeo/ie/54/75?distance=0&format=json").accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isBadRequest())
                .andExpect(content().string("Wrong distance parameter - it has to higher than 0 metres"))
                ;                
                
    }
    
}