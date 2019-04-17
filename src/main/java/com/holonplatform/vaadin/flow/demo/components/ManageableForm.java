package com.holonplatform.vaadin.flow.demo.components;

public interface ManageableForm {
	
	void enableForm(boolean enable);
	
	void clearFields();
	
	void save();
	
	void delete();
	
	void discard();

}
