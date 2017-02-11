package org.fmunozn.addresses.alliescomputing.repository.impl;

import java.util.HashMap;

import org.fmunozn.addresses.alliescomputing.repository.AlliesComputingRepository;
import org.fmunozn.addresses.alliescomputing.request.EircodeRequestBean;
import org.fmunozn.addresses.alliescomputing.request.PremiseRequestBean;
import org.fmunozn.addresses.alliescomputing.response.EircodeResponseBean;
import org.fmunozn.addresses.alliescomputing.response.PremiseResponseBean;
import org.fmunozn.addresses.configuration.properties.AlliesComputingProperties;
import org.fmunozn.addresses.utils.LookupEnum;
import org.fmunozn.addresses.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Repository
public class AlliesComputingRepositoryImpl implements AlliesComputingRepository {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AlliesComputingProperties alliesComputingProperties;

	public AlliesComputingRepositoryImpl(AlliesComputingProperties alliesComputingProperties){

		this.alliesComputingProperties = alliesComputingProperties;

	}


	@Override
	public PremiseResponseBean fullUkPremiseAddressLookup(PremiseRequestBean requestData){

		String urlString = generateUrl("uk", requestData.getFragment(), LookupEnum.LOOKUP_TYPE_ADDRESS.toString(), null, null);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlString);

		HashMap<String,String> parameters  = new HashMap<String,String>();
		parameters.put("lines", requestData.getLines().toString());
		parameters.put("include", requestData.getInclude().toString());
		parameters.put("format", requestData.getFormat());
		parameters.put("addTags", requestData.getAddTags().toString());
		parameters.put("identifier", requestData.getIdentifier());
		parameters.put("callback", requestData.getCallback());
		parameters.put("page", requestData.getPage().toString());
		parameters.put("postcodeonly", requestData.getPostcodeOnly().toString());
		parameters.put("alias", requestData.getAlias().toString());

		Utilities.addRequestsParams(builder, parameters);	

		RestTemplate restTemplate = new RestTemplate();	

		HttpEntity<PremiseResponseBean> response = restTemplate.getForEntity(builder.build().encode().toUri(), PremiseResponseBean.class);

		return response.getBody();
	}

	@Override
	@Cacheable(value="eircodeLookupCache", key="#requestData")
	public EircodeResponseBean eircodeLookup(EircodeRequestBean requestData) {

		String urlString = generateUrl("ie", requestData.getFragment(), LookupEnum.LOOKUP_TYPE_ADDRESS.toString(), null, null);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlString);

		HashMap<String,String> parameters  = new HashMap<String,String>();
		parameters.put("lines", requestData.getLines().toString());
		parameters.put("include", requestData.getInclude().toString());
		parameters.put("format", requestData.getFormat());
		parameters.put("identifier", requestData.getIdentifier());
		parameters.put("callback", requestData.getCallback());
		parameters.put("page", requestData.getPage().toString());

		Utilities.addRequestsParams(builder, parameters);	

		RestTemplate restTemplate = new RestTemplate();	

		HttpEntity<EircodeResponseBean> response = restTemplate.getForEntity(builder.build().encode().toUri(), EircodeResponseBean.class);

		return response.getBody();
	
	}
	
	@Override
	public EircodeResponseBean eircodeAndCoordinateLookup(EircodeRequestBean requestData) {
		
		String urlString = generateUrl("ie", requestData.getFragment(), LookupEnum.LOOKUP_TYPE_ADDRESS_GEOLOCATION.toString(), null, null);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlString);

		HashMap<String,String> parameters  = new HashMap<String,String>();
		parameters.put("lines", requestData.getLines().toString());
		parameters.put("include", requestData.getInclude().toString());
		parameters.put("format", requestData.getFormat());
		parameters.put("identifier", requestData.getIdentifier());
		parameters.put("callback", requestData.getCallback());
		parameters.put("page", requestData.getPage().toString());

		Utilities.addRequestsParams(builder, parameters);	

		RestTemplate restTemplate = new RestTemplate();	

		HttpEntity<EircodeResponseBean> response = restTemplate.getForEntity(builder.build().encode().toUri(), EircodeResponseBean.class);

		return response.getBody();
		
	}


	@Override
	public EircodeResponseBean coordinateLookup(EircodeRequestBean requestData) {
		
		String urlString = generateUrl("ie", requestData.getFragment(), LookupEnum.LOOKUP_TYPE_POSITON.toString(), null, null);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlString);

		HashMap<String,String> parameters  = new HashMap<String,String>();

		parameters.put("format", requestData.getFormat());

		Utilities.addRequestsParams(builder, parameters);	

		RestTemplate restTemplate = new RestTemplate();	

		HttpEntity<EircodeResponseBean> response = restTemplate.getForEntity(builder.build().encode().toUri(), EircodeResponseBean.class);

		return response.getBody();
		
	}


	@Override
	public EircodeResponseBean reverseGeoLookup(EircodeRequestBean requestData) {
		
		String urlString = generateUrl("ie", requestData.getFragment(), LookupEnum.LOOKUP_TYPE_POSITON.toString(), null, null);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlString);

		HashMap<String,String> parameters  = new HashMap<String,String>();

		parameters.put("lines", requestData.getLines().toString());
		parameters.put("include", requestData.getInclude().toString());
		parameters.put("format", requestData.getFormat());
		parameters.put("identifier", requestData.getIdentifier());
		parameters.put("callback", requestData.getCallback());
		parameters.put("page", requestData.getPage().toString());
		parameters.put("distance", requestData.getDistance().toString());

		Utilities.addRequestsParams(builder, parameters);	

		RestTemplate restTemplate = new RestTemplate();	

		HttpEntity<EircodeResponseBean> response = restTemplate.getForEntity(builder.build().encode().toUri(), EircodeResponseBean.class);

		return response.getBody();
	}

	private String generateUrl(String country, String fragment, String requestType, String longitude, String latitude){

		String url = this.alliesComputingProperties.getApiScheme()+"://"+
				this.alliesComputingProperties.getApiHostname()+"/"+
				this.alliesComputingProperties.getApiKey()+"/";
		
		switch (requestType) {
		
		case "ADDRESS":
			
			url = url + this.alliesComputingProperties.getApiAddressUrl()+"/"+country+"/";
			logger.debug("Address without geolocation requested: "+url);
			break;

		case "ADDRESS_GEOLOCATION":

			url = url + this.alliesComputingProperties.getApiGeolocationUrl()+"/"+country+"/";
			logger.debug("Address with geolocation requested: "+url);
			break;
			
		case "POSITION":

			url = url + this.alliesComputingProperties.getApiPositionUrl()+"/"+country+"/";
			logger.debug("Position requested: "+url);
			break;
			
		case "REVERSE_COORDINATES":

			if(longitude != null && latitude != null){
				
				url = url + latitude + "/" + longitude + "/" + this.alliesComputingProperties.getApiReverseGeoUrl()+"/";
				logger.debug("Reverse coordinates requested: "+url);			

								
			}else{
				
				url = null;
				logger.error("latitude or longitude are null");
				
			}

			break;
			
		default:
			url = null;
			logger.error("Wrong Parameter");			
			break;
		}

		return url;

	}
	

}