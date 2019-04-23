package com.holonplatform.vaadin.flow.demo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.vaadin.flow.demo.enums.OrderState;

public interface OrderService {

	Optional<PropertyBox> getOrder(Long orderId);

	Long getDeliveredOrders(LocalDate day);

	Long getDeliveredOrders(LocalDate from, LocalDate to);

	List<Long> getDeliveredOrdersIds(LocalDate from, LocalDate to);

	Long getOrdersByDate(LocalDate day);

	Long getOrdersByStatus(OrderState status);

	Long getOrdersByDateAndStatus(LocalDate day, OrderState status);

	OperationResult save(PropertyBox pbOrder);

}
