package com.holonplatform.vaadin.flow.demo.enums;

public enum UserRole {

	ADMIN("admin"), BAKER("baker"), BARISTA("barista");

	private String role;

	private UserRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

}
