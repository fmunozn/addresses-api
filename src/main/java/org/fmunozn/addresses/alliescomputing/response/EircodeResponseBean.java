package org.fmunozn.addresses.alliescomputing.response;

/**
 * Class Mapping Eircode response
 * @author Fernando Muñoz del Nuevo, fernandomunoz1985@outlook.com
 *
 */
public class EircodeResponseBean extends AddressResponseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4194094394349351857L;
	
	/**
	 * ETRS89 Latitude Co-ordinate
	 */
	private String latitude;

	/**
	 * ETRS89 Longitude Co-ordinate
	 */
	private String longitude;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EircodeResponseBean() {
		super();		
	}
	
}
