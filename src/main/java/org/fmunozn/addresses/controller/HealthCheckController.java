package org.fmunozn.addresses.controller;

import org.fmunozn.addresses.response.APIResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Fernando Muñoz del Nuevo, fernandomunoz1985@outlook.com
 *
 */

@RestController
@RequestMapping(path="/healthcheck")
public class HealthCheckController {
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	    

	@RequestMapping(path="/basic", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody APIResponseBean hello(@RequestParam(value="test", required=false, defaultValue="test") String test) {
		
		logger.debug("Request to basic healthcheck service");
		
		APIResponseBean response = new APIResponseBean();
		
		response.setMessage("I'm alive: "+test);
		
		return response;		
		
	}

}
