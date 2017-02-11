package org.fmunozn.addresses.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.fmunozn.addresses.alliescomputing.request.EircodeRequestBean;
import org.fmunozn.addresses.alliescomputing.response.EircodeResponseBean;
import org.fmunozn.addresses.service.EircodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Fernando Muñoz del Nuevo, fernandomunoz1985@outlook.com
 *
 */
@RestController
public class APIEircodeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EircodeService eircodeService;

	@RequestMapping(path="/address/ie/{fragment}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<EircodeResponseBean> addressLookup(
			@PathVariable String fragment,
			@RequestParam(value="lines", required=false) Integer lines
			) {

		logger.info("Request Eircode Address Service");
		logger.debug("Fragment: "+fragment);
		
		EircodeRequestBean requestData = new EircodeRequestBean();
		requestData.setFragment(fragment);
		requestData.setLines(lines.toString());
		requestData.setFormat("json");		

		List<EircodeResponseBean> eircodeResponses = eircodeService.eircodeLookup(requestData);


		return eircodeResponses;		

	}

	@RequestMapping(path="/addressgeo/ie/{fragment}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ArrayList<EircodeResponseBean> addressGeoLookup(
			@PathVariable String fragment,
			@RequestParam(value="lines", required=false) Long lines,
			@RequestParam(value="addtags", required=false) String addtags
			) {

		ArrayList<EircodeResponseBean> eircodeResponses = new ArrayList<EircodeResponseBean>(); 

		logger.info("Request Address Geo Mock Service");
		logger.debug("Fragment: "+fragment);

		if(fragment.equalsIgnoreCase("Adelaide Road")){

			EircodeResponseBean response = new EircodeResponseBean();
			response.setAddressline1("Dept of Communications, Climate Change and Natural Resources");
			response.setAddressline2("29-31 Adelaide Road");
			response.setSummaryline("Dept of Communications, Climate Change and Natural Resources, 29-31 Adelaide Road, Dublin 2, D02 X285");
			response.setOrganisation("Dept of Communications, Climate Change and Natural Resources");
			response.setNumber("29-31");
			response.setPremise("29-31");
			response.setStreet("Adelaide Road");
			response.setPosttown("Dublin 2");
			response.setCounty("Dublin");
			response.setPostcode("D02 X285");
			response.setLatitude("53.332067");
			response.setLongitude("-6.255492");
			if(addtags != null && addtags.equalsIgnoreCase("w3w")){
				response.setWhat3words("lease.wiped.life");				
			}

			eircodeResponses.add(response);

		}

		return eircodeResponses;		

	}

	@RequestMapping(path="/position/ie/{eircode}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ArrayList<EircodeResponseBean> coordinateLookup(
			@PathVariable String eircode,
			@RequestParam(value="lines", required=false) Long lines) {

		ArrayList<EircodeResponseBean> eircodeResponses = new ArrayList<EircodeResponseBean>(); 

		logger.info("Request Eircode coordinates Mock Service");
		logger.debug("Fragment: "+eircode);

		if(eircode.equalsIgnoreCase("D02X285")){

			EircodeResponseBean response = new EircodeResponseBean();
			response.setLatitude("53.332067");
			response.setLongitude("-6.255492");

			eircodeResponses.add(response);

		}

		return eircodeResponses;		

	}

	@RequestMapping(path="/rgeo/ie/{latitude:.+}/{longitude:.+}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ArrayList<EircodeResponseBean> coordinateReverseLookup(
			@PathVariable String latitude,
			@PathVariable String longitude,
			@RequestParam(value="distance", required=true) Long distance,
			@RequestParam(value="lines", required=false) Long lines) {

		ArrayList<EircodeResponseBean> eircodeResponses = new ArrayList<EircodeResponseBean>(); 

		logger.info("Request Eircode reverse coordinates Mock Service");
		logger.debug("Latitude: "+latitude+ " Longitude: "+longitude);

		if(latitude.equals("53.332067") &&
				longitude.equals("-6.255492") &&
				distance != null &&
				distance == 50L
				){

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			try {
				eircodeResponses = mapper.readValue(
						new File("src/main/resources/reverseCoordResponse.json"), new TypeReference<ArrayList<EircodeResponseBean>>(){});
			} catch (JsonParseException e) {
				logger.error("Problem parsing address file in reverse coordinate lookup"+e.getMessage());
			} catch (JsonMappingException e) {
				logger.error("Problem mapping address file to Json in reverse coordinate lookup. "+e.getMessage());
			} catch (IOException e) {
				logger.error("IO Problem reading address file in reverse coordinate lookup. "+e.getMessage());
			}

		}

		return eircodeResponses;		

	}
	
}