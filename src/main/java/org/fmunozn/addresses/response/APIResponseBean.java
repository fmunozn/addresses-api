package org.fmunozn.addresses.response;

import java.io.Serializable;

/**
 * @author Fernando Muñoz del Nuevo, fernandomunoz1985@outlook.com
 *
 */
public class APIResponseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9095111484975559315L;
		
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
