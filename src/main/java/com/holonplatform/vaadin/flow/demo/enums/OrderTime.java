package com.holonplatform.vaadin.flow.demo.enums;

import com.holonplatform.core.i18n.Caption;

public enum OrderTime {

	@Caption("07:00")
	SEVEN("07:00"),
	@Caption("08:00")
	EIGHT("08:00"),
	@Caption("09:00")
	NINE("09:00"),
	@Caption("10:00")
	TEN("10:00"),
	@Caption("11:00")
	ELEVEN("11:00"),
	@Caption("12:00")
	TWELVE("12:00"),
	@Caption("13:00")
	ONE("13:00"),
	@Caption("14:00")
	TWO("14:00"),
	@Caption("15:00")
	THREE("15:00"),
	@Caption("16:00")
	FOUR("16:00");
	
	private String value;
	
	private OrderTime(String value) {
		this.value = value;
	}
	
	public static OrderTime getEnum(String value) {
		if (value.equals("07:00")) {
			return SEVEN;
		}
		if (value.equals("08:00")) {
			return EIGHT;
		}
		if (value.equals("09:00")) {
			return NINE;
		}
		if (value.equals("10:00")) {
			return TEN;
		}
		if (value.equals("11:00")) {
			return ELEVEN;
		}
		if (value.equals("12:00")) {
			return TWELVE;
		}
		if (value.equals("13:00")) {
			return ONE;
		}
		if (value.equals("14:00")) {
			return TWO;
		}
		if (value.equals("15:00")) {
			return THREE;
		}
		if (value.equals("16:00")) {
			return FOUR;
		}
		return EIGHT; 
	}

	public String getValue() {
		return value;
	}
	
}
