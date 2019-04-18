package com.holonplatform.vaadin.flow.demo.services;

import java.util.List;

import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.core.query.QuerySort;

public interface OrderHistoryService {

	List<PropertyBox> getOrderHistory(Long orderId, QuerySort sort);

	OperationResult save(PropertyBox pbOrderHistory);

}
