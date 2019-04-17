package com.holonplatform.vaadin.flow.demo.forms;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

import com.holonplatform.auth.Account.AccountProvider;
import com.holonplatform.auth.AuthContext;
import com.holonplatform.core.Context;
import com.holonplatform.core.i18n.LocalizationContext;
import com.holonplatform.core.i18n.TemporalFormat;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.Input;
import com.holonplatform.vaadin.flow.demo.data.OrderData;
import com.holonplatform.vaadin.flow.demo.enums.OrderState;
import com.holonplatform.vaadin.flow.demo.models.Customer;
import com.holonplatform.vaadin.flow.demo.models.Order;
import com.holonplatform.vaadin.flow.demo.models.OrderHistory;
import com.holonplatform.vaadin.flow.demo.models.OrderItem;
import com.holonplatform.vaadin.flow.demo.models.User;
import com.holonplatform.vaadin.flow.demo.services.CustomerService;
import com.holonplatform.vaadin.flow.demo.services.OrderDataService;
import com.holonplatform.vaadin.flow.demo.services.OrderHistoryService;
import com.holonplatform.vaadin.flow.demo.services.OrderItemService;
import com.holonplatform.vaadin.flow.demo.services.UserService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class OrderReview extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private PropertyBox pbOrder;
	private List<PropertyBox> pbOrderItems;
	private PropertyBox pbCustomer;
	private List<PropertyBox> pbOrderHistory;
	private Input<String> messageInput;

	private OrderHistoryService orderHistoryService;
	private CustomerService customerService;
	private OrderItemService orderItemService;

	public OrderReview(PropertyBox pbOrder, Consumer<Double> consumer) {
		super();
		this.pbOrder = pbOrder;

		FlexLayout wrapper = new FlexLayout();
		wrapper.setWidth("100%");

		// status and order id
		Div statusDiv = Components.div().width("100px").build();

		if (OrderState.DELIVERED.equals(pbOrder.getValue(Order.STATE))) {
			Icon icon = new Icon(VaadinIcon.CHECK);
			icon.addClassName("icon-delivered");
			statusDiv.add(icon);
		} else {
			statusDiv.add(Components.span().text(pbOrder.getValue(Order.STATE).name())
					.styleNames("status", pbOrder.getValue(Order.STATE).name().toLowerCase()).build());
		}

		wrapper.add(statusDiv);
		wrapper.add(Components.span().text("Order #" + pbOrder.getValue(Order.ID)).build());
		wrapper.setJustifyContentMode(JustifyContentMode.BETWEEN);

		// date and customer
		Div orderDateDiv = Components.div().width("120px").styleName("order-date").build();
		// date
		H3 date = Components.h3().styleName("order-day").text(
				DateTimeFormatter.ofPattern("dd MMM").withLocale(Locale.US).format(pbOrder.getValue(Order.DUE_DATE)))
				.build();
		Div lbl = Components.label().text("Due").build();
		orderDateDiv.add(lbl, date);
		orderDateDiv.add(Components.div().text(pbOrder.getValue(Order.DUE_DATE).getDayOfWeek().toString()).build());

		// time
		Div orderTimeDiv = Components.div().width("120px").styleName("order-date").build();
		H3 time = Components.h3().styleName("order-day").text(
				LocalizationContext.require().format(pbOrder.getValue(Order.DUE_TIME), null, TemporalFormat.SHORT))
				.build();
		lbl = Components.label().text("Time").build();
		orderTimeDiv.add(lbl, time);
		// store
		orderTimeDiv.add(Components.div().text("@ " + pbOrder.getValue(Order.PICKUP_LOCATION_DESCRIPTION)).build());

		// customer
		H3 customerName = Components.h3().styleName("customer").text(pbOrder.getValue(Order.CUSTOMER_FULLNAME)).build();
		lbl = Components.label().text("Customer").build();
		Div customerDiv = Components.div().fullWidth().build();
		customerDiv.add(lbl, customerName);

		OrderDataService orderDataService = Context.get().resource(OrderDataService.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve OrderDataService from Context."));

		orderHistoryService = Context.get().resource(OrderHistoryService.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve OrderHistoryService from Context."));

		customerService = Context.get().resource(CustomerService.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve CustomerService from Context."));

		orderItemService = Context.get().resource(OrderItemService.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve OrderItemService from Context."));

		// customer
		pbCustomer = customerService.getCustomer(pbOrder.getValue(Order.CUSTOMER))
				.orElseThrow(() -> new IllegalStateException("Missing customer data."));

		H3 phone = Components.h3().styleName("customer").text(pbCustomer.getValue(Customer.PHONE)).build();
		lbl = Components.label().text("Phone number").build();
		Div phoneDiv = Components.div().fullWidth().build();
		phoneDiv.add(lbl, phone);

		// products
		lbl = Components.label().text("Products").build();
		Div productsDiv = Components.div().width("50%").build();
		productsDiv.add(lbl);

		pbOrderItems = orderItemService.getOrderItems(pbOrder.getValue(Order.ID));

		pbOrderItems.forEach(item -> {
			productsDiv
					.add(Components.hl().fullWidth().styleName("product-row")
							.addAndExpand(Components.div().styleName("product-name")
									.text(item.getValue(OrderItem.PRODUCT_NAME)).build(), 1d)
							.add(Components.span().text(item.getValue(OrderItem.QUANTITY).toString()).styleName("count")
									.build(),
									Components.span()
											.text("x $" + LocalizationContext.require()
													.format(item.getValue(OrderItem.PRODUCT_PRICE), 2))
											.build())
							.build());
		});

		// history
		lbl = Components.label().text("History").build();
		Div historyDiv = Components.div().width("50%").build();
		Div messagesDiv = Components.div().fullWidth().build();
		historyDiv.add(lbl);
		historyDiv.add(messagesDiv);
		pbOrderHistory = getHistoryMessages(pbOrder.getValue(Order.ID));

		UserService userService = Context.get().resource(UserService.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve UserService from Context."));

		pbOrderHistory.forEach(message -> {
			String userName = userService.getUserName(message.getValue(OrderHistory.CREATED_BY)).orElse("");
			messagesDiv.add(addMessageRow(userName, message.getValue(OrderHistory.TIMESTAMP),
					message.getValue(OrderHistory.MESSAGE)));
		});

		// username
		String userName = (String) Context.get().resource(AccountProvider.class).get()
				.loadAccountById(AuthContext.getCurrent().get().getAuthentication().get().getName()).get().getDetails()
				.get(User.USER_DETAIL_NAME);

		// user ID
		Long userId = (Long) Context.get().resource(AccountProvider.class).get()
				.loadAccountById(AuthContext.getCurrent().get().getAuthentication().get().getName()).get().getDetails()
				.get(User.USER_DETAIL_ID);

		historyDiv.add((messageInput = Components.input.string().fullWidth().placeholder("Add comment")
				.suffixComponent(Components.button().text("Send").onClick(evt -> {
					if (messageInput.getValue() != null && !messageInput.getValue().isEmpty()) {
						messagesDiv.add(addMessageRow(userName, LocalDateTime.now(), messageInput.getValue()));
						orderDataService.saveMessage(pbOrder.getValue(Order.ID), userId, messageInput.getValue());
						messageInput.clear();
					}
				}).build()).build()).getComponent());

		Components.configure(this).fullSize().spacing().add(wrapper)
				.add(Components.hl().fullWidth().add(orderDateDiv, orderTimeDiv, customerDiv, phoneDiv).build())
				.add(productsDiv).align(productsDiv, Alignment.CENTER).add(historyDiv)
				.align(historyDiv, Alignment.CENTER);

		consumer.accept(getOrderPrice());

	}

	public OrderData getOrderData() {
		// retrieve messages loaded now
		pbOrderHistory = getHistoryMessages(pbOrder.getValue(Order.ID));
		return OrderData.builder().order(pbOrder).orderItems(pbOrderItems).customer(pbCustomer)
				.orderHistory(pbOrderHistory).build();
	}

	private List<PropertyBox> getHistoryMessages(Long orderId) {
		return orderHistoryService.getOrderHistory(orderId, OrderHistory.TIMESTAMP.asc());
	}

	private static HorizontalLayout addMessageRow(String userName, LocalDateTime time, String message) {
		return Components.hl().fullWidth().spacing()
				.add(Components.span().text(userName).styleName("user-name").build(),
						Components.span()
								.text(LocalizationContext.require().format(time, TemporalFormat.MEDIUM,
										TemporalFormat.SHORT))
								.build(),
						Components.span().text(message).build())
				.build();
	}

	public Double getOrderPrice() {
		Double orderPrice = 0.00d;
		if (pbOrderItems != null && !pbOrderItems.isEmpty()) {
			orderPrice = pbOrderItems.stream()
					.mapToDouble(pb -> pb.getValue(OrderItem.PRODUCT_PRICE) * pb.getValue(OrderItem.QUANTITY)).sum();
		}
		return orderPrice;
	}

}
