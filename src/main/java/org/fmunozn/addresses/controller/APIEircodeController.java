package org.fmunozn.addresses.controller;

import java.util.ArrayList;
import java.util.List;

import org.fmunozn.addresses.alliescomputing.request.EircodeRequestBean;
import org.fmunozn.addresses.alliescomputing.response.EircodeResponseBean;
import org.fmunozn.addresses.exception.APIEircodeRequestValidationException;
import org.fmunozn.addresses.service.EircodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


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
			@RequestParam(value="lines", required=false) Long lines,
			@RequestParam(value="include", required=false) Boolean include,
			@RequestParam(value="exclude", required=false) Boolean exclude,
			@RequestParam(value="identifier", required=false) String identifier,
			@RequestParam(value="callback", required=false) String callback,
			@RequestParam(value="page", required=false) Long page
			) throws APIEircodeRequestValidationException {

		logger.info("Request Eircode Address Service");
		logger.debug("Fragment: "+fragment);

		EircodeRequestBean requestData = parseToEircodeRequestBean(fragment, lines, include, exclude,
				page, null, null, null, null); 		
		requestData.setIdentifier(identifier);
		requestData.setCallback(callback);
		requestData.setFormat("json");
		
		List<EircodeResponseBean> eircodeResponses = null;
		
		eircodeResponses = eircodeService.eircodeLookup(requestData);

		return eircodeResponses;		

	}

	@RequestMapping(path="/addressgeo/ie/{fragment}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<EircodeResponseBean> addressGeoLookup(
			@PathVariable String fragment,
			@RequestParam(value="lines", required=false) Long lines,
			@RequestParam(value="addtags", required=false) String addtags,
			@RequestParam(value="include", required=false) Boolean include,
			@RequestParam(value="exclude", required=false) Boolean exclude,
			@RequestParam(value="identifier", required=false) String identifier,
			@RequestParam(value="callback", required=false) String callback,
			@RequestParam(value="page", required=false) Long page
			) throws APIEircodeRequestValidationException {

		logger.info("Request Address Geo Service");
		logger.debug("Fragment: "+fragment);


		EircodeRequestBean requestData = parseToEircodeRequestBean(fragment, lines, include, exclude,
				page, null, null, null, null);
		requestData.setAddTags(addtags);
		requestData.setIdentifier(identifier);
		requestData.setCallback(callback);
		requestData.setFormat("json");		
		
		
		List<EircodeResponseBean> eircodeResponses = eircodeService.eircodeAndCoordinateLookup(requestData);

		return eircodeResponses;		

	}

	@RequestMapping(path="/position/ie/{eircode}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<EircodeResponseBean> coordinateLookup(@PathVariable String eircode) throws APIEircodeRequestValidationException {

		logger.info("Request Eircode coordinates Service");
		logger.debug("Fragment: "+eircode);

		ArrayList<EircodeResponseBean> eircodeResponses = new ArrayList<EircodeResponseBean>(); 
		Boolean valid = validateEircode(eircode);

		if(valid){

			logger.debug("Valid Eircode: "+eircode);

			EircodeRequestBean requestData = new EircodeRequestBean();
			requestData.setFragment(eircode);
			requestData.setFormat("json");

			eircodeService.coordinateLookup(requestData);

		}

		return eircodeResponses;		

	}

	@RequestMapping(path="/rgeo/ie/{latitude:.+}/{longitude:.+}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<EircodeResponseBean> coordinateReverseLookup(
			@PathVariable Double latitude,
			@PathVariable Double longitude,
			@RequestParam(value="lines", required=false) Long lines,
			@RequestParam(value="distance", required=true) Long distance,
			@RequestParam(value="include", required=false) Boolean include,
			@RequestParam(value="exclude", required=false) Boolean exclude,
			@RequestParam(value="identifier", required=false) String identifier,
			@RequestParam(value="callback", required=false) String callback,
			@RequestParam(value="page", required=false) Long page
			) throws APIEircodeRequestValidationException{

		logger.info("Request Eircode reverse coordinates Service");
		logger.debug("Latitude: "+latitude+ " Longitude: "+longitude);

		EircodeRequestBean requestData = parseToEircodeRequestBean(null, lines, include, exclude,
				page, null, latitude, longitude, distance); 
		requestData.setFormat("json");		

		List<EircodeResponseBean> eircodeResponses = eircodeService.reverseGeoLookup(requestData);

		return eircodeResponses;

	}


	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { APIEircodeRequestValidationException.class })
	public @ResponseBody String handleValidation( Exception e) { 
				
		return e.getMessage();
		
	}


	/**
	 * Method to validate request parameters
	 * @param fragment
	 * @param lines
	 * @param include
	 * @param exclude
	 * @param page
	 * @param addtags
	 * @param latitude
	 * @param longitude
	 * @param distance
	 * @return
	 * @throws APIEircodeRequestValidationException
	 */
	private EircodeRequestBean parseToEircodeRequestBean(
			String fragment,
			Long lines,
			Boolean include,
			Boolean exclude,
			Long page,
			String addtags,
			Double latitude,
			Double longitude,
			Long distance) throws APIEircodeRequestValidationException {

		EircodeRequestBean requestData = new EircodeRequestBean();

		// We define a 5 chars as minimum searchable fragment
		// This way we will avoid unnecesary requests to the API
		if(fragment != null){

			if(fragment.length() > 5){			

				requestData.setFragment(fragment);

			}else{

				throw new APIEircodeRequestValidationException("Empty or wrong fragment - it has to be at least 5 chars length");

			}
		}else{

			if(latitude != null){

				if(latitude >= -90 && latitude <= 90){

					requestData.setLatitude(latitude.toString());

				}else{

					throw new APIEircodeRequestValidationException("Wrong latitude parameter - it has to be between -90 and +90");			

				}

			}

			if(longitude != null){

				if(longitude >= -180 && longitude <= 180){

					requestData.setLongitude(longitude.toString());

				}else{

					throw new APIEircodeRequestValidationException("Wrong longitude parameter - it has to be between -180 and +180");			

				}

			}	

			if(distance != null){

				if(distance >= 1){

					requestData.setDistance(distance.toString());

				}else{

					throw new APIEircodeRequestValidationException("Wrong distance parameter - it has to higher than 0 metres");			

				}

			}

			requestData.setFragment(fragment);


		}

		if(lines != null) {

			if(lines < 4 && lines > 0){

				requestData.setLines(lines.toString());

			}else{

				throw new APIEircodeRequestValidationException("Wrong number of additional lines - it has to be between 1 and 4");			

			}

		}

		if(include != null)			
			requestData.setInclude(include.toString());

		if(exclude != null)
			requestData.setExclude(exclude.toString());

		if(page != null) {
			
			if(page >= 0){
				
				requestData.setPage(page.toString());
				
			}else{
				
				throw new APIEircodeRequestValidationException("Wrong page number - it has to be 0 or more");			
				
			}
		}
			



		return requestData;

	}

	/**
	 * Method to validate eircode lenght
	 * @param eircode
	 * @return
	 */
	private Boolean validateEircode(String eircode){

		Boolean valid = false;

		if(eircode.length() == 7)
			valid = true;

		return valid;

	}

}