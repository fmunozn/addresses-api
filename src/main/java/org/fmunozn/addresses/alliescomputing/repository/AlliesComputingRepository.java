package org.fmunozn.addresses.alliescomputing.repository;

import java.util.List;

import org.fmunozn.addresses.alliescomputing.request.EircodeRequestBean;
import org.fmunozn.addresses.alliescomputing.request.PremiseRequestBean;
import org.fmunozn.addresses.alliescomputing.response.EircodeResponseBean;
import org.fmunozn.addresses.alliescomputing.response.PremiseResponseBean;

public interface AlliesComputingRepository {	
	
	public PremiseResponseBean fullUkPremiseAddressLookup(PremiseRequestBean requestData);
	
	public List<EircodeResponseBean> eircodeLookup(EircodeRequestBean requestData);
	
	public List<EircodeResponseBean> eircodeAndCoordinateLookup(EircodeRequestBean requestData);
	
	public List<EircodeResponseBean> coordinateLookup(EircodeRequestBean requestData);
	
	public List<EircodeResponseBean> reverseGeoLookup(EircodeRequestBean requestData);
	
	public void evictRequestDataByFragment(String string);
	
	public List<EircodeResponseBean> eircodeLookupWatchdog(EircodeRequestBean requestData, EircodeRequestBean watchdog);

	
}
