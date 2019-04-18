package com.holonplatform.vaadin.flow.demo.models;

public final class OrderDestination {
	
	public static final String STORE = "Store";
	public static final String BAKERY = "Bakery";
	
	private OrderDestination() {}
	
	public static String[] values() {
		return new String[] {STORE, BAKERY};
	}

}
