package org.fmunozn.addresses.utils;

/**
 * 
 * @author Fernando Muñoz del Nuevo, fernandomunoz1985@outlook.com
 *
 */
public enum LookupEnum {

	LOOKUP_TYPE_ADDRESS("ADDRESS"),
	LOOKUP_TYPE_ADDRESS_GEOLOCATION("ADDRESS_GEOLOCATION"),
	LOOKUP_TYPE_POSITON("POSITION"),
	LOOKUP_TYPE_REVERSE_COORDINATES("REVERSE_COORDINATES");
	
    private final String lookupType;


    /**
     * 
     * @param lookupType
     */
    private LookupEnum(final String lookupType) {
        this.lookupType = lookupType;
    }

    @Override
    public String toString() {
        return lookupType;
    }
}
	
