package org.fmunozn.addresses.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Class to map Allies Computing API properties
 * @author Fernando Muñoz del Nuevo, fernandomunoz1985@outlook.com
 *
 */
@PropertySource(value="classpath:alliescomputing.properties")
@Component
public class AlliesComputingProperties {
	
	/**
	 * API Key needed to authenticate with the endpoint 
	 */
	@Value("${alliescomputing.api.key}")
	private String apiKey;
	
	/**
	 * http or https 
	 */
	@Value("${alliescomputing.api.scheme}")
	private String apiScheme;
	
	/**
	 * Endpoint name
	 */
	@Value("${alliescomputing.api.hostname}")
	private String apiHostname;
	
	/**
	 * Address service url
	 */
	@Value("${alliescomputing.api.address.url}")
	private String apiAddressUrl;
	
	/**
	 * Address Geo service url
	 */
	@Value("${alliescomputing.api.geo.url}")
	private String apiGeolocationUrl;
	
	/**
	 * Address Geo service url
	 */
	@Value("${alliescomputing.api.position.url}")
	private String apiPositionUrl;

	
	/**
	 * Address Geo service url
	 */
	@Value("${alliescomputing.api.geo.reverse.url}")
	private String apiReverseGeoUrl;

	
	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiScheme() {
		return apiScheme;
	}

	public void setApiScheme(String apiScheme) {
		this.apiScheme = apiScheme;
	}

	public String getApiHostname() {
		return apiHostname;
	}

	public void setApiHostname(String apiHostname) {
		this.apiHostname = apiHostname;
	}

	public String getApiAddressUrl() {
		return apiAddressUrl;
	}

	public void setApiAddressUrl(String apiAddressUrl) {
		this.apiAddressUrl = apiAddressUrl;
	}

	public String getApiGeolocationUrl() {
		return apiGeolocationUrl;
	}

	public void setApiGeolocationUrl(String apiGeolocationUrl) {
		this.apiGeolocationUrl = apiGeolocationUrl;
	}

	public String getApiPositionUrl() {
		return apiPositionUrl;
	}

	public void setApiPositionUrl(String apiPositionUrl) {
		this.apiPositionUrl = apiPositionUrl;
	}

	public String getApiReverseGeoUrl() {
		return apiReverseGeoUrl;
	}

	public void setApiReverseGeoUrl(String apiReverseGeoUrl) {
		this.apiReverseGeoUrl = apiReverseGeoUrl;
	}

}
