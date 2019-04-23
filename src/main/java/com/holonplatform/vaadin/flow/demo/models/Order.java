package com.holonplatform.vaadin.flow.demo.models;

import java.time.LocalDate;
import java.time.LocalTime;

import com.holonplatform.core.Context;
import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.property.BooleanProperty;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.PathProperty;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.PropertyValueConverter;
import com.holonplatform.core.property.TemporalProperty;
import com.holonplatform.core.property.VirtualProperty;
import com.holonplatform.vaadin.flow.demo.enums.OrderState;

public interface Order {

	public static final NumericProperty<Long> ID = NumericProperty.create("id", Long.class);
	public static final TemporalProperty<LocalDate> DUE_DATE = TemporalProperty.create("duedate", LocalDate.class);
	public static final TemporalProperty<LocalTime> DUE_TIME = TemporalProperty.create("duetime", LocalTime.class);
	public static final BooleanProperty PAID = BooleanProperty.create("paid")
			.converter(PropertyValueConverter.numericBoolean(Integer.class));
	public static final NumericProperty<Long> CUSTOMER = NumericProperty.create("customer", Long.class);
	public static final PathProperty<OrderState> STATE = PathProperty.create("state", OrderState.class)
			.converter(PropertyValueConverter.enumByName());
	public static final NumericProperty<Integer> PICKUP_LOCATION = NumericProperty.create("pickup_location",
			Integer.class);

	// customer fullname
	public static final VirtualProperty<String> CUSTOMER_FULLNAME = VirtualProperty.create(String.class, row -> {
		Datastore ds = Context.get().resource(Datastore.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve Datastore from Context."));
		return ds.query(Customer.TARGET).filter(Customer.ID.eq(row.getValue(CUSTOMER))).findOne(Customer.FULLNAME)
				.orElse("");
	});

	// pickup location name
	public static final VirtualProperty<String> PICKUP_LOCATION_DESCRIPTION = VirtualProperty.create(String.class,
			propertyBox -> {
				Datastore ds = Context.get().resource(Datastore.class).get();
				return ds.query(PickupLocation.TARGET)
						.filter(PickupLocation.ID.eq(propertyBox.getValue(Order.PICKUP_LOCATION)))
						.findOne(PickupLocation.LOCATION).get();
			});

	public static final PropertySet<?> ORDER = PropertySet.builderOf(ID, DUE_DATE, DUE_TIME, PAID, CUSTOMER, STATE,
			PICKUP_LOCATION, CUSTOMER_FULLNAME, PICKUP_LOCATION_DESCRIPTION).identifier(ID).build();

	public static final DataTarget<?> TARGET = DataTarget.named("orders");

}
