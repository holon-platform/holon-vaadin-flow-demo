package com.holonplatform.vaadin.flow.demo.services;

import java.util.List;

import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.Property;
import com.holonplatform.core.property.PropertyBox;

public interface OrderItemService {

	List<PropertyBox> getOrderItems(Long orderId);

	List<PropertyBox> getOrderItems(Long orderId, Property... properties);

	Long orderItemsCount(Integer productId);

	OperationResult save(PropertyBox pbOrderItem);

	OperationResult delete(PropertyBox pbOrderItem);

}
