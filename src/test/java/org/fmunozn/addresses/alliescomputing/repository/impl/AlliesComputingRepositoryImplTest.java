package org.fmunozn.addresses.alliescomputing.repository.impl;

import org.fmunozn.addresses.alliescomputing.repository.AlliesComputingRepository;
import org.fmunozn.addresses.alliescomputing.request.EircodeRequestBean;
import org.fmunozn.addresses.configuration.AddressesApiApplication;
import org.fmunozn.addresses.exception.APIEircodeRequestValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressesApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class AlliesComputingRepositoryImplTest {
	
	// Cache Testing
	
	@Autowired
	private AlliesComputingRepository repository;
	
	@Test
	public void successIfMethodEircodeLookupExecutedOnce() throws APIEircodeRequestValidationException{
		
		repository.evictRequestDataByFragment("D02X285");
		
		EircodeRequestBean erb1 = new EircodeRequestBean();
		erb1.setFragment("D02X285");
		
		EircodeRequestBean erb2 = new EircodeRequestBean();
		erb2.setFragment("D02X285");	
		
		EircodeRequestBean mockWatchdog = Mockito.mock(EircodeRequestBean.class);
		
		repository.eircodeLookupWatchdog(erb1, mockWatchdog);
		repository.eircodeLookupWatchdog(erb2, mockWatchdog);
		
	    Mockito.verify(mockWatchdog, Mockito.times(1)).getFragment();

		
	}

}
