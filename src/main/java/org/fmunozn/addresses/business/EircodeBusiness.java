package org.fmunozn.addresses.business;

import java.util.List;

import org.fmunozn.addresses.alliescomputing.request.EircodeRequestBean;
import org.fmunozn.addresses.alliescomputing.request.PremiseRequestBean;
import org.fmunozn.addresses.alliescomputing.response.EircodeResponseBean;
import org.fmunozn.addresses.alliescomputing.response.PremiseResponseBean;
import org.fmunozn.addresses.exception.APIEircodeRequestValidationException;

/**
 * 
 * @author Fernando Muñoz del Nuevo, fernandomunoz1985@outlook.com
 *
 */
public interface EircodeBusiness {
	
	
	public List<EircodeResponseBean> eircodeLookup(EircodeRequestBean requestData) throws APIEircodeRequestValidationException;
	
	public List<EircodeResponseBean> eircodeAndCoordinateLookup(EircodeRequestBean requestData) throws APIEircodeRequestValidationException;
	
	public List<EircodeResponseBean> coordinateLookup(EircodeRequestBean requestData) throws APIEircodeRequestValidationException;
	
	public List<EircodeResponseBean> reverseGeoLookup(EircodeRequestBean requestData) throws APIEircodeRequestValidationException;

	public List<PremiseResponseBean> fullUkPremiseAddressLookup(PremiseRequestBean requestData);

}
