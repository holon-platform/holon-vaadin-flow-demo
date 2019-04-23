package com.holonplatform.vaadin.flow.demo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.DefaultWriteOption;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.internal.utils.ObjectUtils;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.vaadin.flow.demo.enums.OrderState;
import com.holonplatform.vaadin.flow.demo.models.Order;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private Datastore datastore;

	@Override
	public Optional<PropertyBox> getOrder(Long orderId) {
		return datastore.query(Order.TARGET).filter(Order.ID.eq(orderId)).findOne(Order.ORDER);
	}

	@Override
	public Long getDeliveredOrders(LocalDate day) {
		ObjectUtils.argumentNotNull(day, "Missing day parameter");
		return datastore.query(Order.TARGET).filter(Order.DUE_DATE.eq(day).and(Order.STATE.eq(OrderState.DELIVERED)))
				.count();
	}

	@Override
	public Long getDeliveredOrders(LocalDate from, LocalDate to) {
		ObjectUtils.argumentNotNull(from, "Missing day parameter");
		ObjectUtils.argumentNotNull(to, "Missing day parameter");
		return datastore.query(Order.TARGET)
				.filter(Order.DUE_DATE.between(from, to).and(Order.STATE.eq(OrderState.DELIVERED))).count();
	}

	@Override
	public List<Long> getDeliveredOrdersIds(LocalDate from, LocalDate to) {
		return datastore.query(Order.TARGET)
				.filter(Order.STATE.eq(OrderState.DELIVERED).and(Order.DUE_DATE.between(from, to))).list(Order.ID);
	}

	@Override
	public Long getOrdersByDate(LocalDate day) {
		ObjectUtils.argumentNotNull(day, "Missing day parameter");
		return datastore.query(Order.TARGET).filter(Order.DUE_DATE.eq(day)).count();
	}

	@Override
	public Long getOrdersByStatus(OrderState status) {
		ObjectUtils.argumentNotNull(status, "Missing status parameter");
		return datastore.query(Order.TARGET).filter(Order.STATE.eq(status)).count();
	}

	@Override
	public Long getOrdersByDateAndStatus(LocalDate day, OrderState status) {
		ObjectUtils.argumentNotNull(day, "Missing day parameter");
		ObjectUtils.argumentNotNull(status, "Missing status parameter");
		return datastore.query(Order.TARGET).filter(Order.DUE_DATE.eq(day).and(Order.STATE.eq(status))).count();
	}

	@Override
	public OperationResult save(PropertyBox pbOrder) {
		return datastore.save(Order.TARGET, pbOrder, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

}
