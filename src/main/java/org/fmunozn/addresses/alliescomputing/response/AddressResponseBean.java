package org.fmunozn.addresses.alliescomputing.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Class mapping common elements from Eircode and Premise
 * @author Fernando Muñoz del Nuevo, fernandomunoz1985@outlook.com
 *
 */
@JsonInclude(Include.NON_NULL)
public class AddressResponseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4936781640857789349L;

	/**
	 * Complete postal address in a single line with commas separating the elements.
	 */
	private String summaryline;
	
	private String addressline1;
	
	private String addressline2;
	
	private String addressline3;
	
	private String organisation;
	
	private String buildingname;
	
	/**
	 * Composite of all premise level elements including department, building name, sub building name, number and PO Box where applicable.	
	 */
	private String premise;
	
	private String dependentstreet;
	
	private String street;
	
	private String number;
	
	private String doubledependentlocality;
	
	private String dependentlocality;
	
	private String posttown;
	
	private String county;
	
	private String postcode;
	
	private String what3words;
	
	private String buildinggroupname;
	
	/**
	 * Indicates at least one more page of addresses for the search	
	 */
	private Boolean morevalues;
	
	/**
	 * Page number for the next page of results	
	 */
	private String nextpage;
	
	/**
	 * Total number of addresses for the search (only shown if more than 100 results)	
	 */
	private String totalresults;
	
	public String getSummaryline() {
		return summaryline;
	}

	public void setSummaryline(String summaryline) {
		this.summaryline = summaryline;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getBuildingname() {
		return buildingname;
	}

	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}

	public String getPremise() {
		return premise;
	}

	public void setPremise(String premise) {
		this.premise = premise;
	}

	public String getDependentstreet() {
		return dependentstreet;
	}

	public void setDependentstreet(String dependentstreet) {
		this.dependentstreet = dependentstreet;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDoubledependentlocality() {
		return doubledependentlocality;
	}

	public void setDoubledependentlocality(String doubledependentlocality) {
		this.doubledependentlocality = doubledependentlocality;
	}

	public String getDependentlocality() {
		return dependentlocality;
	}

	public void setDependentlocality(String dependentlocality) {
		this.dependentlocality = dependentlocality;
	}

	public String getPosttown() {
		return posttown;
	}

	public void setPosttown(String posttown) {
		this.posttown = posttown;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}	
	
	public Boolean getMorevalues() {
		return morevalues;
	}

	public void setMorevalues(Boolean morevalues) {
		this.morevalues = morevalues;
	}

	public String getNextpage() {
		return nextpage;
	}

	public void setNextpage(String nextpage) {
		this.nextpage = nextpage;
	}

	public String getTotalresults() {
		return totalresults;
	}

	public void setTotalresults(String totalresults) {
		this.totalresults = totalresults;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getAddressline3() {
		return addressline3;
	}

	public void setAddressline3(String addressline3) {
		this.addressline3 = addressline3;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getWhat3words() {
		return what3words;
	}

	public void setWhat3words(String what3words) {
		this.what3words = what3words;
	}

	public String getBuildinggroupname() {
		return buildinggroupname;
	}

	public void setBuildinggroupname(String buildinggroupname) {
		this.buildinggroupname = buildinggroupname;
	}


	
}
