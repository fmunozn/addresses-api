package org.fmunozn.addresses.alliescomputing.response;

/**
 * 
 * @author Fernando Muñoz del Nuevo, fernandomunoz1985@outlook.com
 *
 */
public class PremiseResponseBean extends AddressResponseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4194094394349351857L;
	
	
	/**
	 * 	PO Box
	 */
	private String pobox;
	
	private String departmentname;
	
	private String subbuildingname;
	
	/**
	 * Colon separated list of the previous postcode, previous delivery point suffix and the date it changed (YYYYMM format)
	 */
	private String recodes;
		
	/**
	 * Indicates address is an alias record, only present when used with true value
	 */
	private Boolean alias;

	public String getPobox() {
		return pobox;
	}

	public void setPobox(String pobox) {
		this.pobox = pobox;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getSubbuildingname() {
		return subbuildingname;
	}

	public void setSubbuildingname(String subbuildingname) {
		this.subbuildingname = subbuildingname;
	}


	public String getRecodes() {
		return recodes;
	}

	public void setRecodes(String recodes) {
		this.recodes = recodes;
	}

	public Boolean getAlias() {
		return alias;
	}

	public void setAlias(Boolean alias) {
		this.alias = alias;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PremiseResponseBean() {
		super();	
	}
	
	
}
