package com.holonplatform.vaadin.flow.demo.models;

import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.StringProperty;

public interface Customer {
	
	public static final NumericProperty<Long> ID = NumericProperty.create("id", Long.class);
	public static final StringProperty FULLNAME = StringProperty.create("fullname");
	public static final StringProperty PHONE = StringProperty.create("phone_number");
	public static final StringProperty DETAILS = StringProperty.create("details");

	public static final PropertySet<?> CUSTOMER = PropertySet.of(ID, FULLNAME, PHONE, DETAILS);
	public static final DataTarget<?> TARGET = DataTarget.named("customers");
	
}
