package com.raafstudio.goahead.people.model;

import java.util.ArrayList;

public class Province {
	String stateID;
	String stateName;
	String stateCode;
	String showOnACS;
	ArrayList<City> Cities = new ArrayList<City>();

	public String getStateID() {
		return stateID;
	}

	public void setStateID(String stateID) {
		this.stateID = stateID;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getShowOnACS() {
		return showOnACS;
	}

	public void setShowOnACS(String showOnACS) {
		this.showOnACS = showOnACS;
	}

	public ArrayList<City> getCities() {
		return Cities;
	}

	public void setCities(ArrayList<City> cities) {
		Cities = cities;
	}

}
