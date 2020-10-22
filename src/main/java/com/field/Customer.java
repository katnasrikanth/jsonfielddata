package com.field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Customer {
	
	
	  private Integer intValue;
	  private String strValue;
	  
	public Integer getIntValue() {
		return intValue;
	}
	public void setIntValue(Integer intValue) {
		this.intValue = intValue;
	}
	public String getStrValue() {
		return strValue;
	}
	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}
	@Override
	public String toString() {
		return "Customer [intValue=" + intValue + ", strValue=" + strValue + "]";
	}

}
