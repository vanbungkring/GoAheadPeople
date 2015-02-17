package com.raafstudio.goahead.people.model;

public class Item {
	String code = "";
	String desc = "";
	
	public Item(  String _code, String _desc )
	{
	    code = _code;
	    desc = _desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
 

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return desc;
	}
}
