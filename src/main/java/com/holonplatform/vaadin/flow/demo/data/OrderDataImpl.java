package com.holonplatform.vaadin.flow.demo.data;

import java.util.List;

import com.holonplatform.core.property.PropertyBox;

public class OrderDataImpl implements OrderData {

	private PropertyBox order;
	private PropertyBox customer;
	private List<PropertyBox> orderItems;
	private List<PropertyBox> orderHistory;

	private OrderDataImpl() {
	}

	public void setOrder(PropertyBox order) {
		this.order = order;
	}

	public void setCustomer(PropertyBox customer) {
		this.customer = customer;
	}

	public void setOrderItems(List<PropertyBox> orderItems) {
		this.orderItems = orderItems;
	}

	public void setOrderHistory(List<PropertyBox> orderHistory) {
		this.orderHistory = orderHistory;
	}

	@Override
	public PropertyBox getOrder() {
		return order;
	}

	@Override
	public PropertyBox getCustomer() {
		return customer;
	}

	@Override
	public List<PropertyBox> getOrderItems() {
		return orderItems;
	}

	@Override
	public List<PropertyBox> getOrderHistory() {
		return orderHistory;
	}

	public static final class BuilderImpl implements OrderData.Builder {

		private final OrderDataImpl orderDataInstance = new OrderDataImpl();

		@Override
		public Builder order(PropertyBox value) {
			orderDataInstance.setOrder(value);
			return this;
		}

		@Override
		public Builder customer(PropertyBox value) {
			orderDataInstance.setCustomer(value);
			return this;
		}

		@Override
		public Builder orderItems(List<PropertyBox> values) {
			orderDataInstance.setOrderItems(values);
			return this;
		}

		@Override
		public Builder orderHistory(List<PropertyBox> values) {
			orderDataInstance.setOrderHistory(values);
			return this;
		}

		@Override
		public OrderData build() {
			return orderDataInstance;
		}
	}

}
