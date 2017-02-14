package org.fmunozn.addresses.alliescomputing.repository;

import java.util.List;

import org.fmunozn.addresses.alliescomputing.request.EircodeRequestBean;
import org.fmunozn.addresses.alliescomputing.request.PremiseRequestBean;
import org.fmunozn.addresses.alliescomputing.response.EircodeResponseBean;
import org.fmunozn.addresses.alliescomputing.response.PremiseResponseBean;
import org.fmunozn.addresses.exception.APIEircodeRequestValidationException;

public interface AlliesComputingRepository {	
	
	public PremiseResponseBean fullUkPremiseAddressLookup(PremiseRequestBean requestData);
	
	public List<EircodeResponseBean> eircodeLookup(EircodeRequestBean requestData) throws APIEircodeRequestValidationException;
	
	public List<EircodeResponseBean> eircodeAndCoordinateLookup(EircodeRequestBean requestData) throws APIEircodeRequestValidationException;
	
	public List<EircodeResponseBean> coordinateLookup(EircodeRequestBean requestData) throws APIEircodeRequestValidationException;
	
	public List<EircodeResponseBean> reverseGeoLookup(EircodeRequestBean requestData) throws APIEircodeRequestValidationException;
	
	public void evictRequestDataByFragment(String string) throws APIEircodeRequestValidationException;
	
	public List<EircodeResponseBean> eircodeLookupWatchdog(EircodeRequestBean requestData, EircodeRequestBean watchdog) throws APIEircodeRequestValidationException;
	
}
