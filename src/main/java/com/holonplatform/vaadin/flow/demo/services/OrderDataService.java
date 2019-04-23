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
