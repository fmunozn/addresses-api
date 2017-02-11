package org.fmunozn.addresses.alliescomputing.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.fmunozn.addresses.alliescomputing.request.EircodeRequestBean;
import org.fmunozn.addresses.alliescomputing.response.EircodeResponseBean;
import org.fmunozn.addresses.configuration.AddressesApiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AddressesApiApplication.class)
public class AlliesComputingRepositoryImplTest {

    @Autowired
    private AlliesComputingRepositoryImpl repository;

    @Test
    public void getAddressBasicTest() throws Exception {
    	
    	EircodeRequestBean requestData = new EircodeRequestBean();
    	requestData.setFragment("D02X285");
    	requestData.setLines("3");
    	requestData.setFormat("json");
    	
    	List<EircodeResponseBean> response = repository.eircodeLookup(requestData);
    	assertNotNull(response);
        assertEquals("Dept of Communications, Climate Change and Natural Resources", response.get(0).getAddressline1());
    }

}