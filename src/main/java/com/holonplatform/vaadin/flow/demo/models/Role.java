package com.holonplatform.vaadin.flow.demo.models;

import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.property.Property;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.StringProperty;

public interface Role {

	public static final StringProperty ID = StringProperty.create("id");
	public static final StringProperty DESCRIPTION = StringProperty.create("description");

	public static final PropertySet<Property<?>> ROLE = PropertySet.builderOf(ID, DESCRIPTION).identifier(ID).build();
	public static final DataTarget<String> TARGET = DataTarget.named("roles");

}
