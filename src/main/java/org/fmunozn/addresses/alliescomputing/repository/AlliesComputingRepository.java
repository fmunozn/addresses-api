package org.fmunozn.addresses.alliescomputing.repository;

import java.util.List;

import org.fmunozn.addresses.alliescomputing.request.EircodeRequestBean;
import org.fmunozn.addresses.alliescomputing.request.PremiseRequestBean;
import org.fmunozn.addresses.alliescomputing.response.EircodeResponseBean;
import org.fmunozn.addresses.alliescomputing.response.PremiseResponseBean;

public interface AlliesComputingRepository {	
	
	public PremiseResponseBean fullUkPremiseAddressLookup(PremiseRequestBean requestData);
	
	public List<EircodeResponseBean> eircodeLookup(EircodeRequestBean requestData);
	
	public EircodeResponseBean eircodeAndCoordinateLookup(EircodeRequestBean requestData);
	
	public EircodeResponseBean coordinateLookup(EircodeRequestBean requestData);
	
	public EircodeResponseBean reverseGeoLookup(EircodeRequestBean requestData);

	
}
