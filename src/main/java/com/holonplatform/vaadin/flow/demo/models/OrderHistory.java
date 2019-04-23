package com.holonplatform.vaadin.flow.demo.models;

import java.time.LocalDateTime;

import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.StringProperty;
import com.holonplatform.core.property.TemporalProperty;

public interface OrderHistory {

	public static final NumericProperty<Long> ORDER_ID = NumericProperty.create("order_id", Long.class);
	public static final NumericProperty<Long> ID = NumericProperty.create("id", Long.class);
	public static final StringProperty MESSAGE = StringProperty.create("message");
	public static final TemporalProperty<LocalDateTime> TIMESTAMP = TemporalProperty.create("timestamp",
			LocalDateTime.class);
	public static final NumericProperty<Long> CREATED_BY = NumericProperty.create("created_by", Long.class);

	public static final PropertySet<?> ORDER_HISTORY = PropertySet
			.builderOf(ORDER_ID, ID, MESSAGE, TIMESTAMP, CREATED_BY).identifier(ORDER_ID).identifier(ID).build();

	public static final DataTarget<?> TARGET = DataTarget.named("order_history");

}
