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
	private Integer lines;
	
	/**
	 * Include extra address fields within the address lines returned
	 */
	private Boolean include;
	
	/**
	 * Exclude address fields within the address lines returned
	 */
	private Boolean exclude;

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
	private Long page;
	
	/**
	 * 	Add extra address fields such as UDPRN to the return
	 */
	private Boolean addTags;

	public String getFragment() {
		return fragment;
	}

	public void setFragment(String fragment) {
		this.fragment = fragment;
	}

	public Integer getLines() {
		return lines;
	}

	public void setLines(Integer lines) {
		this.lines = lines;
	}

	public Boolean getInclude() {
		return include;
	}

	public void setInclude(Boolean include) {
		this.include = include;
	}

	public Boolean getExclude() {
		return exclude;
	}

	public void setExclude(Boolean exclude) {
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

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getAddTags() {
		return addTags;
	}

	public void setAddTags(Boolean addTags) {
		this.addTags = addTags;
	}
	

}
