/*
 * Copyright 2016-2018 Axioma srl.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.holonplatform.vaadin.flow.demo.data;

import java.util.List;

import com.holonplatform.core.property.PropertyBox;

public class OrderDataImpl implements OrderData {
	
	private PropertyBox order;
	private PropertyBox customer;
	private List<PropertyBox> orderItems; 
	private List<PropertyBox> orderHistory;
	private Double totalPrice;
	
	private OrderDataImpl() {}
	
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

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
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

	@Override
	public Double getTotalPrice() {
		return totalPrice;
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
		public Builder totalPrice(Double value) {
			orderDataInstance.setTotalPrice(value);
			return this;
		}

		@Override
		public OrderData build() {
			return orderDataInstance;
		}	
	}
	
}
