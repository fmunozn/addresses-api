package org.fmunozn.addresses.exception;

/**
 * 
 * @author Fernando Muñoz del Nuevo, fernandomunoz1985@outlook.com
 *
 */
public class APIEircodeRequestValidationException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1239884003667224371L;

	public APIEircodeRequestValidationException() {}

    public APIEircodeRequestValidationException(String message)
    {
       super(message);
    }
	
}
