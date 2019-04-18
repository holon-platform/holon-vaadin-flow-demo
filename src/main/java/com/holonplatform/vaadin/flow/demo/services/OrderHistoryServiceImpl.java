package com.holonplatform.vaadin.flow.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.datastore.DefaultWriteOption;
import com.holonplatform.core.internal.utils.ObjectUtils;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.core.query.Query;
import com.holonplatform.core.query.QuerySort;
import com.holonplatform.vaadin.flow.demo.models.OrderHistory;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

	@Autowired
	private Datastore datastore;

	@Override
	public List<PropertyBox> getOrderHistory(Long orderId, QuerySort sort) {
		ObjectUtils.argumentNotNull(orderId, "Missing order Id");
		Query query = datastore.query(OrderHistory.TARGET).filter(OrderHistory.ORDER_ID.eq(orderId));
		if (sort != null) {
			query.sort(sort);
		}
		return query.list(OrderHistory.ORDER_HISTORY);
	}

	@Override
	public OperationResult save(PropertyBox pbOrderHistory) {
		ObjectUtils.argumentNotNull(pbOrderHistory, "Missing order history PropertyBox");
		return datastore.save(OrderHistory.TARGET, pbOrderHistory, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

}
