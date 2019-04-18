package com.holonplatform.vaadin.flow.demo.models;

import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.Property;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.StringProperty;

public interface PickupLocation {

	public static final NumericProperty<Integer> ID = NumericProperty.create("ID", Integer.class);
	public static final StringProperty LOCATION = StringProperty.create("LOCATION");

	public static final PropertySet<Property<?>> PICKUP_LOCATION = PropertySet.of(ID, LOCATION);
	public static final DataTarget<?> TARGET = DataTarget.named("pickup_location");
}
