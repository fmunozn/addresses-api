package org.fmunozn.addresses.service.impl;

import java.util.List;

import org.fmunozn.addresses.alliescomputing.request.EircodeRequestBean;
import org.fmunozn.addresses.alliescomputing.request.PremiseRequestBean;
import org.fmunozn.addresses.alliescomputing.response.EircodeResponseBean;
import org.fmunozn.addresses.alliescomputing.response.PremiseResponseBean;
import org.fmunozn.addresses.business.EircodeBusiness;
import org.fmunozn.addresses.exception.APIEircodeRequestValidationException;
import org.fmunozn.addresses.service.EircodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Fernando Muñoz del Nuevo, fernandomunoz1985@outlook.com
 *
 */
@Service
public class EircodeServiceImpl implements EircodeService {

	@Autowired
	EircodeBusiness eircodeBusiness;
	


	@Override
	public List<EircodeResponseBean> eircodeLookup(EircodeRequestBean requestData) throws APIEircodeRequestValidationException {
		return eircodeBusiness.eircodeLookup(requestData);
	}

	@Override
	public List<EircodeResponseBean> eircodeAndCoordinateLookup(EircodeRequestBean requestData) throws APIEircodeRequestValidationException {
		return eircodeBusiness.eircodeAndCoordinateLookup(requestData);
	}

	@Override
	public List<EircodeResponseBean> coordinateLookup(EircodeRequestBean requestData) throws APIEircodeRequestValidationException {
		return eircodeBusiness.coordinateLookup(requestData);
	}

	@Override
	public List<EircodeResponseBean> reverseGeoLookup(EircodeRequestBean requestData) throws APIEircodeRequestValidationException {
		return eircodeBusiness.reverseGeoLookup(requestData);
	}
	
	@Override
	public List<PremiseResponseBean> fullUkPremiseAddressLookup(PremiseRequestBean requestData) {
		// TODO Auto-generated method stub
		return null;
	}
}
