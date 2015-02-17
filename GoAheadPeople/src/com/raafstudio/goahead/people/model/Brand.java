package com.raafstudio.goahead.people.model;

import java.util.ArrayList;

public class Brand {
	String brandID;
	String brandName;
	String brandCode;
	String marketID;
	String showOnACS;
	ArrayList<SubBrand> subBrand = new ArrayList<SubBrand>();

	public String getBrandID() {
		return brandID;
	}

	public void setBrandID(String brandID) {
		this.brandID = brandID;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getMarketID() {
		return marketID;
	}

	public void setMarketID(String marketID) {
		this.marketID = marketID;
	}

	public String getShowOnACS() {
		return showOnACS;
	}

	public void setShowOnACS(String showOnACS) {
		this.showOnACS = showOnACS;
	}

	public ArrayList<SubBrand> getSubBrand() {
		return subBrand;
	}

	public void setSubBrand(ArrayList<SubBrand> subBrand) {
		this.subBrand = subBrand;
	}

}
