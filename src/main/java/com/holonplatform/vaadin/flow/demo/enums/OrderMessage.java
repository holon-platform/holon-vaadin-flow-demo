package com.holonplatform.vaadin.flow.demo.enums;

public enum OrderMessage {

	PLACED("Order placed"), 
	CONFIRMED("Order confirmed"), 
	READY("Order ready"), 
	DELIVERED("Order delivered");

	private String message;

	private OrderMessage(String message) {
		this.message = message;
	}

	private String getMessage() {
		return message;
	}
	
	public static String getValue(OrderMessage m) {
		return m.getMessage();
	}
}
