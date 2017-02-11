package org.fmunozn.addresses.business;

import java.util.List;

import org.fmunozn.addresses.alliescomputing.request.EircodeRequestBean;
import org.fmunozn.addresses.alliescomputing.request.PremiseRequestBean;
import org.fmunozn.addresses.alliescomputing.response.EircodeResponseBean;
import org.fmunozn.addresses.alliescomputing.response.PremiseResponseBean;

/**
 * 
 * @author Fernando Muñoz del Nuevo, fernandomunoz1985@outlook.com
 *
 */
public interface EircodeBusiness {
	
	public List<PremiseResponseBean> fullUkPremiseAddressLookup(PremiseRequestBean requestData);
	
	public List<EircodeResponseBean> eircodeLookup(EircodeRequestBean requestData);
	
	public List<EircodeResponseBean> eircodeAndCoordinateLookup(EircodeRequestBean requestData);
	
	public List<EircodeResponseBean> coordinateLookup(EircodeRequestBean requestData);
	
	public List<EircodeResponseBean> reverseGeoLookup(EircodeRequestBean requestData);

}
