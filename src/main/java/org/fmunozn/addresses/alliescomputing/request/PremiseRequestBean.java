package org.fmunozn.addresses.alliescomputing.request;

/**
 * Class to map parameters for an UK premise request
 * @author Fernando Muñoz del Nuevo, fernandomunoz1985@outlook.com
 *
 */
public class PremiseRequestBean extends AddressRequestBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2236145216696543371L;
	

	
	/**
	 * 	Use true to limit your search to just the postcode field.
	 */
	private String postcodeOnly;
	
	/**
	 * Use true to include alternative names for addresses known to and accepted by Royal Mail
	 */
	private String alias;
	


	
	public String getPostcodeOnly() {
		return postcodeOnly;
	}

	public void setPostcodeOnly(String postcodeOnly) {
		this.postcodeOnly = postcodeOnly;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}		

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PremiseRequestBean() {
		super();
	}


	

}
