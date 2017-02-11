package org.fmunozn.addresses.alliescomputing.request;

import java.io.Serializable;

/**
 * Class mapping generic request parameters for an address lookup by postcode or address
 * @author Fernando Muñoz del Nuevo, fernandomunoz1985@outlook.com
 *
 */
public class AddressRequestBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2236145216696543371L;
	
	/**
	 * postcode-or-address-fragment
	 *  
	 */
	private String fragment;
	
	/**
	 * 	The number of lines over which to split each address
	 */
	private String lines;
	
	/**
	 * Include extra address fields within the address lines returned
	 */
	private String include;
	
	/**
	 * Exclude address fields within the address lines returned
	 */
	private String exclude;

	/**
	 * xml | json (default unless header of application/xml is detected)
	 */
	private String format;
	
	
	/**
	 * Identify your lookups to make debugging and reviewing stats easier
	 */
	private String identifier;
	
	/**
	 * Use to specify the name of your JSONP callback function
	 */
	private String callback;

	/**
	 * 	For use with searches that return more than 100 results (first page is 0)
	 */
	private String page;
	
	/**
	 * 	Add extra address fields such as UDPRN to the return
	 */
	private String addTags;

	public String getFragment() {
		return fragment;
	}

	public void setFragment(String fragment) {
		this.fragment = fragment;
	}

	public String getLines() {
		return lines;
	}

	public void setLines(String lines) {
		this.lines = lines;
	}

	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	public String getExclude() {
		return exclude;
	}

	public void setExclude(String exclude) {
		this.exclude = exclude;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAddTags() {
		return addTags;
	}

	public void setAddTags(String addTags) {
		this.addTags = addTags;
	}
	

}
