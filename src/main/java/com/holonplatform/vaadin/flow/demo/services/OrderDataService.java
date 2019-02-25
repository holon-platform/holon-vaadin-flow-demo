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
package com.holonplatform.vaadin.flow.demo.services;

import java.util.List;
import java.util.Map;

import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.vaadin.flow.demo.data.OrderData;

public interface OrderDataService {

	PropertyBox getOrder(Long oderId);

	PropertyBox getOrderCustomer(Long customerId);

	List<PropertyBox> getOrderItems(Long orderId);

	List<PropertyBox> getOrderHistory(Long orderId);

	void saveMessage(Long orderId, Long userId, String message);

	void placeOrUpdateOrder(OrderData orderData);

	// analytics

	Long getTodayOrders();

	Long getTodayDeliveries();

	Long getTodayProblems();

	Long getNewOrders();

	Long getTomorrowOrders();

	Map<String, Number> getCurrentMonthDeliveries();

	List<Number> getDeliveriesPerMonth();

	List<Number> getSalesPerYear(int year);

	Map<String, Number> getDeliveredProductsLastMonth();

}
