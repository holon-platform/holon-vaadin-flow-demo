package com.holonplatform.vaadin.flow.demo.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.relational.RelationalTarget;
import com.holonplatform.core.exceptions.DataAccessException;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.vaadin.flow.demo.data.OrderData;
import com.holonplatform.vaadin.flow.demo.enums.OrderState;
import com.holonplatform.vaadin.flow.demo.models.Customer;
import com.holonplatform.vaadin.flow.demo.models.Order;
import com.holonplatform.vaadin.flow.demo.models.OrderHistory;
import com.holonplatform.vaadin.flow.demo.models.OrderItem;
import com.holonplatform.vaadin.flow.demo.models.Product;

@Service
public class OrderDataServiceImpl implements OrderDataService {

	@Autowired
	private Datastore datastore;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderHistoryService orderHistoryService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderItemService orderItemService;

	@Override
	public PropertyBox getOrder(Long orderId) {
		return orderService.getOrder(orderId).orElseThrow(() -> new DataAccessException("Order not found"));
	}

	@Override
	public PropertyBox getOrderCustomer(Long customerId) {
		return customerService.getCustomer(customerId).orElseThrow(() -> new DataAccessException("Customer not found"));
	}

	@Override
	public List<PropertyBox> getOrderItems(Long orderId) {
		return orderItemService.getOrderItems(orderId);
	}

	@Override
	public List<PropertyBox> getOrderHistory(Long orderId) {
		return orderHistoryService.getOrderHistory(orderId, null);
	}

	@Override
	public void saveMessage(Long orderId, Long userId, String message) {
		PropertyBox pbHistory = PropertyBox.builder(OrderHistory.ORDER_HISTORY).set(OrderHistory.ORDER_ID, orderId)
				.set(OrderHistory.CREATED_BY, userId).set(OrderHistory.MESSAGE, message)
				.set(OrderHistory.TIMESTAMP, LocalDateTime.now()).build();
		orderHistoryService.save(pbHistory);
	}

	@Transactional
	@Override
	public void placeOrUpdateOrder(OrderData orderData) {

		PropertyBox pbCustomer = orderData.getCustomer();
		PropertyBox pbOrder = orderData.getOrder();
		List<PropertyBox> pbOrderItems = orderData.getOrderItems();

		customerService.save(pbCustomer);
		Long customerId = pbCustomer.getValue(Customer.ID);

		pbOrder.setValue(Order.CUSTOMER, customerId);
		orderService.save(pbOrder);
		Long orderId = pbOrder.getValue(Order.ID);

		pbOrderItems.forEach(pb -> {
			pb.setValue(OrderItem.ORDER, orderId);
			orderItemService.save(pb);
		});
	}

	// Analytics

	@Override
	public Long getTodayOrders() {
		return orderService.getOrdersByDate(LocalDate.now());
	}

	@Override
	public Long getTodayDeliveries() {
		return orderService.getOrdersByDateAndStatus(LocalDate.now(), OrderState.DELIVERED);
	}

	@Override
	public Long getTodayProblems() {
		return orderService.getOrdersByDateAndStatus(LocalDate.now(), OrderState.PROBLEM);
	}

	@Override
	public Long getNewOrders() {
		return orderService.getOrdersByStatus(OrderState.NEW);
	}

	@Override
	public Long getTomorrowOrders() {
		LocalDate tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS);
		return orderService.getOrdersByDate(tomorrow);
	}

	@Override
	public Map<String, Number> getCurrentMonthDeliveries() {
		LocalDate today = LocalDate.now();
		int daysPerMonth = today.lengthOfMonth();
		LocalDate firstDayOfMonth = today.withDayOfMonth(1);

		Map<String, Number> deliveries = new LinkedHashMap<>();

		for (int i = 0; i < daysPerMonth; i++) {
			LocalDate day = firstDayOfMonth.plus(i, ChronoUnit.DAYS);

			deliveries.put(String.valueOf(day.getDayOfMonth()), orderService.getDeliveredOrders(day));
		}
		return deliveries;
	}

	@Override
	public List<Number> getDeliveriesPerMonth() {

		List<Number> deliveries = new LinkedList<>();

		for (Month month : EnumSet.range(Month.JANUARY, Month.DECEMBER)) {
			LocalDate firstDay = LocalDate.of(LocalDate.now().getYear(), month, 1);
			LocalDate lastDay = LocalDate.of(LocalDate.now().getYear(), month, firstDay.lengthOfMonth());

			deliveries.add(orderService.getDeliveredOrders(firstDay, lastDay));
		}
		return deliveries;
	}

	@Override
	public List<Number> getSalesPerYear(int year) {

		List<Number> sales = new LinkedList<>();

		for (Month month : EnumSet.range(Month.JANUARY,
				LocalDate.now().getYear() == year ? LocalDate.now().getMonth() : Month.DECEMBER)) {
			LocalDate firstDay = LocalDate.of(year, month, 1);
			LocalDate lastDay = LocalDate.of(year, month, firstDay.lengthOfMonth());

			List<Long> deliveredOrdersIds = orderService.getDeliveredOrdersIds(firstDay, lastDay);

			Double totalPrice = new Double(0);

			if (deliveredOrdersIds != null && !deliveredOrdersIds.isEmpty()) {
				for (Long id : deliveredOrdersIds) {
					List<PropertyBox> pbItems = orderItemService.getOrderItems(id);
					if (pbItems != null && !pbItems.isEmpty()) {
						for (PropertyBox pb : pbItems) {
							totalPrice = totalPrice
									+ pb.getValue(OrderItem.QUANTITY) * pb.getValue(OrderItem.PRODUCT_PRICE);
						}
					}
				}
			}
			sales.add(totalPrice);
		}
		return sales;
	}

	@Override
	public Map<String, Number> getDeliveredProductsLastMonth() {

		LocalDate today = LocalDate.now();
		int daysPerMonth = today.lengthOfMonth();
		LocalDate firstDayOfMonth = today.withDayOfMonth(1);
		LocalDate lastDayOfMonth = firstDayOfMonth.plus(daysPerMonth - 1, ChronoUnit.DAYS);

		RelationalTarget<?> RT = RelationalTarget.of(Order.TARGET).innerJoin(OrderItem.TARGET)
				.on(Order.TARGET.property(Order.ID).eq(OrderItem.TARGET.property(OrderItem.ORDER))).add();

		Map<String, Number> productCountMap = new HashMap<>();

		// products
		List<PropertyBox> pbProducts = productService.getProducts();
		pbProducts.forEach(pb -> {

			Long count = datastore.query(RT)
					.filter(Order.STATE.eq(OrderState.DELIVERED)
							.and(Order.TARGET.property(Order.DUE_DATE).between(firstDayOfMonth, lastDayOfMonth))
							.and(OrderItem.TARGET.property(OrderItem.PRODUCT).eq(pb.getValue(Product.ID))))
					.count();

			productCountMap.put(pb.getValue(Product.NAME), count);

		});
		return productCountMap;
	}

}
