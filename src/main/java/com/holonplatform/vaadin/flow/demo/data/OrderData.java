package com.holonplatform.vaadin.flow.demo.data;

import java.util.List;

import com.holonplatform.core.property.PropertyBox;

public interface OrderData {

	PropertyBox getOrder();

	PropertyBox getCustomer();

	List<PropertyBox> getOrderItems();

	List<PropertyBox> getOrderHistory();

	static Builder builder() {
		return new OrderDataImpl.BuilderImpl();
	}

	public interface Builder {

		Builder order(PropertyBox value);

		Builder customer(PropertyBox value);

		Builder orderItems(List<PropertyBox> values);

		Builder orderHistory(List<PropertyBox> values);

		OrderData build();
	}

}
