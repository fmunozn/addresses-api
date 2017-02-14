package org.fmunozn.addresses.alliescomputing.repository.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.fmunozn.addresses.alliescomputing.repository.AlliesComputingRepository;
import org.fmunozn.addresses.alliescomputing.request.EircodeRequestBean;
import org.fmunozn.addresses.alliescomputing.request.PremiseRequestBean;
import org.fmunozn.addresses.alliescomputing.response.EircodeResponseBean;
import org.fmunozn.addresses.alliescomputing.response.PremiseResponseBean;
import org.fmunozn.addresses.configuration.properties.AlliesComputingProperties;
import org.fmunozn.addresses.exception.APIEircodeRequestValidationException;
import org.fmunozn.addresses.utils.LookupEnum;
import org.fmunozn.addresses.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
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
	@Cacheable(value="eircodeLookupCache", key="#requestData.fragment")
	public List<EircodeResponseBean> eircodeLookup(EircodeRequestBean requestData) throws APIEircodeRequestValidationException {

		String urlString = generateUrl("ie", requestData.getFragment(), LookupEnum.LOOKUP_TYPE_ADDRESS.toString(), null, null);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlString);

		HashMap<String,String> parameters  = new HashMap<String,String>();
		parameters.put("lines", requestData.getLines());
		parameters.put("include", requestData.getInclude());
		parameters.put("format", requestData.getFormat());
		parameters.put("identifier", requestData.getIdentifier());
		parameters.put("callback", requestData.getCallback());
		parameters.put("page", requestData.getPage());

		Utilities.addRequestsParams(builder, parameters);	

		RestTemplate restTemplate = new RestTemplate();
		
		try{
			
			HttpEntity<EircodeResponseBean []> response = restTemplate.getForEntity(builder.build().encode().toUri(), EircodeResponseBean[].class);
			return Arrays.asList(response.getBody());

		}catch(HttpClientErrorException hcee){
			
			throw new APIEircodeRequestValidationException("Wrong Eircode or Address requested");
			
		}
	
	}
	
	@Override
	@Cacheable(value="eircodeLookupCache", key="#requestData")
	public List<EircodeResponseBean> eircodeAndCoordinateLookup(EircodeRequestBean requestData) throws APIEircodeRequestValidationException {
		
		String urlString = generateUrl("ie", requestData.getFragment(), LookupEnum.LOOKUP_TYPE_ADDRESS_GEOLOCATION.toString(), null, null);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlString);

		HashMap<String,String> parameters  = new HashMap<String,String>();
		parameters.put("lines", requestData.getLines());
		parameters.put("include", requestData.getInclude());
		parameters.put("format", requestData.getFormat());
		parameters.put("identifier", requestData.getIdentifier());
		parameters.put("callback", requestData.getCallback());
		parameters.put("page", requestData.getPage());
		parameters.put("addtags", requestData.getAddTags());

		Utilities.addRequestsParams(builder, parameters);	

		RestTemplate restTemplate = new RestTemplate();	
		try{
			
			HttpEntity<EircodeResponseBean []> response = restTemplate.getForEntity(builder.build().encode().toUri(), EircodeResponseBean[].class);
			return Arrays.asList(response.getBody());

		}catch(HttpClientErrorException hcee){
			
			throw new APIEircodeRequestValidationException("Wrong Eircode or Address requested");
			
		}
	}


	@Override
	@Cacheable(value="eircodeLookupCache", key="#requestData")
	public List<EircodeResponseBean> coordinateLookup(EircodeRequestBean requestData) throws APIEircodeRequestValidationException {
		
		String urlString = generateUrl("ie", requestData.getFragment(), LookupEnum.LOOKUP_TYPE_POSITON.toString(), null, null);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlString);

		HashMap<String,String> parameters  = new HashMap<String,String>();

		parameters.put("format", requestData.getFormat());

		Utilities.addRequestsParams(builder, parameters);	

		RestTemplate restTemplate = new RestTemplate();	
		
		try{
		
			HttpEntity<EircodeResponseBean []> response = restTemplate.getForEntity(builder.build().encode().toUri(), EircodeResponseBean[].class);
			return Arrays.asList(response.getBody());

		}catch(HttpClientErrorException hcee){
			
			throw new APIEircodeRequestValidationException("Wrong Eircode or Address requested");
			
		}
		
		
	}


	@Override
	@Cacheable(value="eircodeLookupCache", key="#requestData")
	public List<EircodeResponseBean> reverseGeoLookup(EircodeRequestBean requestData) throws APIEircodeRequestValidationException {
		
		String urlString = generateUrl("ie", requestData.getFragment(), LookupEnum.LOOKUP_TYPE_REVERSE_COORDINATES.toString(), requestData.getLongitude(), requestData.getLatitude());

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlString);

		HashMap<String,String> parameters  = new HashMap<String,String>();

		parameters.put("lines", requestData.getLines());
		parameters.put("include", requestData.getInclude());
		parameters.put("format", requestData.getFormat());
		parameters.put("identifier", requestData.getIdentifier());
		parameters.put("callback", requestData.getCallback());
		parameters.put("page", requestData.getPage());
		parameters.put("distance", requestData.getDistance());

		Utilities.addRequestsParams(builder, parameters);	

		RestTemplate restTemplate = new RestTemplate();	

		try{
			
			HttpEntity<EircodeResponseBean []> response = restTemplate.getForEntity(builder.build().encode().toUri(), EircodeResponseBean[].class);
			logger.debug("Reverse Geo Body:" +response.toString());
			
			return Arrays.asList(response.getBody());

		}catch(HttpClientErrorException hcee){
			
			throw new APIEircodeRequestValidationException("Wrong Eircode or Address requested");
			
		}
		
	}
	
	@CacheEvict(value="eircodeLookupCache")
	public void evictRequestDataByFragment(String fragment) {

	}
	
	@Override
	@Cacheable(value="eircodeLookupCache", key="#requestData.fragment")
	public List<EircodeResponseBean> eircodeLookupWatchdog(EircodeRequestBean requestData, EircodeRequestBean watchdog) throws APIEircodeRequestValidationException {

		watchdog.getFragment();
		
		String urlString = generateUrl("ie", requestData.getFragment(), LookupEnum.LOOKUP_TYPE_ADDRESS.toString(), null, null);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlString);

		HashMap<String,String> parameters  = new HashMap<String,String>();
		parameters.put("lines", requestData.getLines());
		parameters.put("include", requestData.getInclude());
		parameters.put("format", requestData.getFormat());
		parameters.put("identifier", requestData.getIdentifier());
		parameters.put("callback", requestData.getCallback());
		parameters.put("page", requestData.getPage());

		Utilities.addRequestsParams(builder, parameters);	

		RestTemplate restTemplate = new RestTemplate();
		

		try{
			
			HttpEntity<EircodeResponseBean []> response = restTemplate.getForEntity(builder.build().encode().toUri(), EircodeResponseBean[].class);
			return Arrays.asList(response.getBody());

		}catch(HttpClientErrorException hcee){
			
			throw new APIEircodeRequestValidationException("Wrong Eircode or Address requested");
			
		}
		
	}
	
	
	@Override
	public PremiseResponseBean fullUkPremiseAddressLookup(PremiseRequestBean requestData){

		String urlString = generateUrl("uk", requestData.getFragment(), LookupEnum.LOOKUP_TYPE_ADDRESS.toString(), null, null);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlString);

		HashMap<String,String> parameters  = new HashMap<String,String>();
		parameters.put("lines", requestData.getLines().toString());
		parameters.put("include", requestData.getInclude());
		parameters.put("format", requestData.getFormat());
		parameters.put("addTags", requestData.getAddTags());
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

	private String generateUrl(String country, String fragment, String requestType, String longitude, String latitude){

		String url = this.alliesComputingProperties.getApiScheme()+"://"+
				this.alliesComputingProperties.getApiHostname()+"/"+
				this.alliesComputingProperties.getApiKey()+"/";
		
		switch (requestType) {
		
		case "ADDRESS":
			
			url = url + this.alliesComputingProperties.getApiAddressUrl()+"/"+country+"/"+fragment;
			logger.debug("Address without geolocation requested: "+url);
			break;

		case "ADDRESS_GEOLOCATION":

			url = url + this.alliesComputingProperties.getApiGeolocationUrl()+"/"+country+"/"+fragment;
			logger.debug("Address with geolocation requested: "+url);
			break;
			
		case "POSITION":

			url = url + this.alliesComputingProperties.getApiPositionUrl()+"/"+country+"/"+fragment;
			logger.debug("Position requested: "+url);
			break;
			
		case "REVERSE_COORDINATES":

			if(longitude != null && latitude != null){
				
				url = url  + this.alliesComputingProperties.getApiReverseGeoUrl()+"/" + country + "/" + latitude + "/" + longitude;
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
