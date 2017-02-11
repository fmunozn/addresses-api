package org.fmunozn.addresses.alliescomputing.request;

/**
 * This class is not needed since it has no especial parameter than Address Request Bean. In the future, this can have special
 * information that might have differences with generic address requests
 * @author Fernando Muñoz del Nuevo, fernandomunoz1985@outlook.com
 *
 */
public class EircodeRequestBean extends AddressRequestBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6541235955770721155L;
	
	/**
	 * Distance in metres to perform request with geocoordinates.
	 */
	private Long distance;
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}	

	public EircodeRequestBean() {
		super();
	}


	
}
