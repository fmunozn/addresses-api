package org.fmunozn.addresses.utils;

import java.util.HashMap;
import java.util.Iterator;

import org.springframework.web.util.UriComponentsBuilder;

/**
 * Support methods to be used across the whole application
 * @author Fernando Muñoz del Nuevo, fernandomunoz1985@outlook.com
 *
 */
public class Utilities {

	/**
	 * Method to allow adding to an HTTP request multiple parameters avoiding adding null parameters to the request
	 * @param builder UriComponentsBuilder to be used in the request
	 * @param parameters set of parameters to be added
	 */
	public static void addRequestsParams(UriComponentsBuilder builder, HashMap<String,String> parameters){

		Iterator<String> keysIterator = parameters.keySet().iterator();

		while(keysIterator.hasNext()){

			String currentKey = keysIterator.next();
			String currentValue = parameters.get(currentKey);
			if(currentValue!=null){
				builder.queryParam(currentKey, currentValue);
			}

		}

	}
	
	
}
