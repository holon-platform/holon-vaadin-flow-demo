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
package com.holonplatform.vaadin.flow.demo.forms;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.holonplatform.core.Context;
import com.holonplatform.core.Validator;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.i18n.LocalizationContext;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.Input;
import com.holonplatform.vaadin.flow.components.SingleSelect;
import com.holonplatform.vaadin.flow.components.ValidatableInput;
import com.holonplatform.vaadin.flow.components.builders.ThemableFlexComponentConfigurator.VerticalLayoutConfigurator;
import com.holonplatform.vaadin.flow.demo.data.OrderData;
import com.holonplatform.vaadin.flow.demo.enums.OrderState;
import com.holonplatform.vaadin.flow.demo.enums.OrderTime;
import com.holonplatform.vaadin.flow.demo.models.Customer;
import com.holonplatform.vaadin.flow.demo.models.Order;
import com.holonplatform.vaadin.flow.demo.models.OrderItem;
import com.holonplatform.vaadin.flow.demo.models.PickupLocation;
import com.holonplatform.vaadin.flow.demo.models.Product;
import com.holonplatform.vaadin.flow.demo.services.OrderDataService;
import com.holonplatform.vaadin.flow.demo.services.OrderItemService;
import com.holonplatform.vaadin.flow.demo.services.ProductService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextFieldVariant;

public class OrderManageForm extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private Datastore datastore;
	private ProductService productService;
	private OrderItemService orderItemService;
	private ValidatableInput<OrderState> orderstatusSelect;
	private VerticalLayoutConfigurator vlConfigurator;

	private ValidatableInput<LocalDate> dateInput;
	private ValidatableInput<OrderTime> timeSelect;
	private ValidatableInput<Integer> pickupSelect;
	private ValidatableInput<String> customer;
	private ValidatableInput<String> phone;
	private Input<String> details;

	private HashMap<Long, ProductBox> orderItemsMap;
	private Long orderItemSequence;

	private Consumer<Double> consumer;
	private OrderData orderData;
	private Runnable runnable;

	public OrderManageForm(OrderData orderData, Consumer<Double> consumer, Runnable runnable) {
		super();
		this.orderData = orderData;
		this.consumer = consumer;
		this.runnable = runnable;

		datastore = Context.get().resource(Datastore.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve Datastore from Context."));

		productService = Context.get().resource(ProductService.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve ProductService from Context."));

		orderItemService = Context.get().resource(OrderItemService.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve OrderItemService from Context."));

		// holds all Product Boxes loaded inside the form
		orderItemsMap = new HashMap<>();
		// global Product Boxes counter
		orderItemSequence = 0L;

		vlConfigurator = Components.configure(this).fullWidth();

		// set header or status + order number
		if (orderData != null) {
			// status
			vlConfigurator.add(Components.hl().fullWidth().justifyContentMode(JustifyContentMode.BETWEEN)
					.add(orderstatusSelect = Components.input.singleSelect(OrderState.class).items(OrderState.values())
							.validatable().withValidator(Validator.notNull()).build())
					.add(Components.span().text("Order #" + orderData.getOrder().getValue(Order.ID)).build()).build());
			orderstatusSelect.setValue(orderData.getOrder().getValue(Order.STATE));
		} else {
			vlConfigurator.add(Components.h2().text("New order").build());
		}

		// order header
		vlConfigurator.add(Components.hl().fullWidth().withoutMargin()
				// due
				.add(Components.vl().sizeUndefined()
						.add(dateInput = Components.input.localDate().label("Due").validatable()
								.withValidator(Validator.notNull()).build())
						.add(timeSelect = Components.input.enumSelect(OrderTime.class).validatable()
								.withValidator(Validator.notNull()).build())
						.add(pickupSelect = Components.input.singleSelect(PickupLocation.ID).required()
								.dataSource(datastore, PickupLocation.TARGET, PickupLocation.PICKUP_LOCATION)
								.itemCaptionProperty(PickupLocation.LOCATION).preventInvalidInput().validatable()
								.withValidator(Validator.notNull()).build())
						.build())
				// customer
				.addAndExpand(Components.vl().fullWidth()
						.add(customer = Components.input.string().label("Customer").required()
								.prefixComponent(new Icon(VaadinIcon.USER)).fullWidth().validatable()
								.withValidator(Validator.notEmpty()).build())
						.add(phone = Components.input.string().label("Phone number").required()
								.prefixComponent(new Icon(VaadinIcon.PHONE)).fullWidth().validatable()
								.withValidator(Validator.notEmpty()).build())
						.add(details = Components.input.string().label("Additional details").fullWidth().build())
						.build(), 1d)
				.build()).add(Components.label().text("Products").build());

		// load saved order data
		if (orderData != null) {
			loadOrderHeader();
			loadProductsBoxes();
			consumer.accept(getOrderPrice());
		}
		// new order
		else {
			ProductBox productBox = new ProductBox(null);
			vlConfigurator.add(productBox);
			orderItemsMap.put(orderItemSequence, productBox);

			timeSelect.setValue(OrderTime.EIGHT);
			dateInput.setValue(LocalDate.now());
		}
	}

	public Double getOrderPrice() {
		Double orderPrice = 0.00d;
		if (orderItemsMap != null && !orderItemsMap.isEmpty()) {
			orderPrice = orderItemsMap.values().stream().mapToDouble(box -> box.getTotalPrice()).sum();
		}
		return orderPrice;
	}

	private void loadOrderHeader() {
		PropertyBox pbOrder = orderData.getOrder();
		dateInput.setValue(pbOrder.getValue(Order.DUE_DATE));
		timeSelect.setValue(
				OrderTime.getEnum(pbOrder.getValue(Order.DUE_TIME).format(DateTimeFormatter.ofPattern("HH:mm"))));
		pickupSelect.setValue(pbOrder.getValue(Order.PICKUP_LOCATION));

		// customer info
		PropertyBox pbCustomer = orderData.getCustomer();
		customer.setValue(pbCustomer.getValue(Customer.FULLNAME));
		phone.setValue(pbCustomer.getValue(Customer.PHONE));
		details.setValue(pbCustomer.getValue(Customer.DETAILS));
	}

	private void loadProductsBoxes() {
		List<PropertyBox> pbItems = orderData.getOrderItems();
		// load all saved order items inside ProductBox
		if (pbItems != null && !pbItems.isEmpty()) {
			pbItems.forEach(pb -> {
				ProductBox box = new ProductBox(pb);
				vlConfigurator.add(box);
				orderItemsMap.put(orderItemSequence, box);
				orderItemSequence++;
			});
		}
		// always add an empty ProductBox to add other items
		ProductBox box = new ProductBox(null);
		vlConfigurator.add(box);
		orderItemsMap.put(orderItemSequence, box);

	}

	// component to load informations about order items
	private class ProductBox extends VerticalLayout {

		private static final long serialVersionUID = 1L;

		private Long orderItemSeq;
		private Double productPrice;
		private Double totalPrice;

		private SingleSelect<Integer> product;
		private Input<String> productCounter;
		private Input<String> productDetails;
		private Button btnClose;
		private Span priceSpan;

		private PropertyBox pbOrderItem;

		private ProductBox(PropertyBox pbOrderItem) {
			this.pbOrderItem = pbOrderItem;
			this.orderItemSeq = orderItemSequence;
			this.productPrice = 0.00d;
			this.totalPrice = 0.00d;

			Components.configure(this).withoutMargin().withoutPadding()
					.add(Components.hl().fullWidth().spacing().withoutMargin()
							// product select
							.addAndExpand(product = Components.input.singleSelect(Product.ID)
									.dataSource(datastore, Product.TARGET, Product.PRODUCT)
									.itemCaptionProperty(Product.NAME).withValueChangeListener(evt -> {
										// enable other components
										productCounter.getElement().setEnabled(true);
										btnClose.setEnabled(true);
										productDetails.getElement().setEnabled(true);
										// add new ProductBox
										if (evt.getOldValue() == null && pbOrderItem == null) {
											// update component counter
											orderItemSequence++;

											ProductBox pb = new ProductBox(null);
											orderItemsMap.put(orderItemSequence, pb);
											vlConfigurator.add(pb);
										}
									}).withSelectionListener(evt -> {
										if (evt.getFirstSelectedItem().isPresent()) {
											// set product price
											Integer productId = evt.getFirstSelectedItem().get();
											productPrice = productService.getPrice(productId).orElse(0.00d);
											Integer quantity = Integer.valueOf(productCounter.getValue());
											totalPrice = productPrice * quantity;
											priceSpan
													.setText("$" + LocalizationContext.require().format(totalPrice, 2));

											// consumer: set window footer with total order price
											consumer.accept(getOrderPrice());
										}
									}).build(), 1d)
							// product counter component
							.add(productCounter = Components.input.string().withValue("1").sizeUndefined().disabled()
									.preventInvalidInput().pattern("|1[0-5]?|[2-9]")
									.withThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER)
									.prefixComponent(Components
											.button().icon(VaadinIcon.MINUS).withThemeVariants(ButtonVariant.LUMO_ICON,
													ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY)
											.onClick(evt -> {
												Integer counter = Integer.valueOf(productCounter.getValue());
												if (counter > 1) {
													productCounter.setValue(String.valueOf(counter - 1));
													totalPrice = Double.valueOf((counter - 1) * productPrice);
													priceSpan.setText("$" + LocalizationContext.require()
															.format(totalPrice, 2).toString());

													// consumer: set window footer with total order price
													consumer.accept(getOrderPrice());
												}
											}).build())
									.suffixComponent(Components
											.button().icon(VaadinIcon.PLUS).withThemeVariants(ButtonVariant.LUMO_ICON,
													ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY)
											.onClick(evt -> {
												Integer counter = Integer.valueOf(productCounter.getValue());
												if (counter < 15) {
													productCounter.setValue(String.valueOf(counter + 1));
													totalPrice = Double.valueOf((counter + 1) * productPrice);
													priceSpan.setText("$" + LocalizationContext.require()
															.format(totalPrice, 2).toString());

													// consumer: set window footer with total order price
													consumer.accept(getOrderPrice());
												}
											}).build())
									.build())
							// close btn
							.add(btnClose = Components.button().icon(VaadinIcon.CLOSE_SMALL)
									.withThemeVariants(ButtonVariant.LUMO_ICON).disabled().onClick(evt -> {
										OrderManageForm.this.remove(orderItemsMap.get(orderItemSeq));
										orderItemsMap.remove(orderItemSeq);

										// consumer: set window footer with total order price
										consumer.accept(getOrderPrice());
									}).build())
							.alignItems(Alignment.BASELINE).build())

					// details and price
					.add(Components.hl().fullWidth().spacing()
							.add(productDetails = Components.input.string().width("85%").placeholder("Details")
									.disabled().build())
							.add(priceSpan = Components.span().sizeUndefined().styleName("total-price").text("$0.00")
									.build())
							.build());

			if (pbOrderItem != null) {
				loadOrderItem();
			}

		}

		private Double getTotalPrice() {
			return totalPrice;
		}

		private void loadOrderItem() {
			product.setValue(pbOrderItem.getValue(OrderItem.PRODUCT));
			productCounter.setValue(pbOrderItem.getValue(OrderItem.QUANTITY).toString());
			productDetails.setValue(pbOrderItem.getValue(OrderItem.COMMENT));
			productPrice = productService.getPrice(pbOrderItem.getValue(OrderItem.PRODUCT)).orElse(0.00d);
			totalPrice = Double.valueOf(pbOrderItem.getValue(OrderItem.QUANTITY) * productPrice);
			priceSpan.setText("$" + LocalizationContext.require().format(totalPrice, 2).toString());
		}

		private Integer getProductId() {
			return product.getValue();
		}

		private Integer getQuantity() {
			return Integer.valueOf(productCounter.getValue());
		}

		private String getComment() {
			return productDetails.getValue();
		}
	}

	public void placeOrder() {

		dateInput.validate();
		customer.validate();
		phone.validate();
		pickupSelect.validate();

		PropertyBox pbOrder;
		PropertyBox pbCustomer;

		if (orderData != null) {

			pbOrder = orderData.getOrder();
			pbOrder.setValue(Order.STATE, orderstatusSelect.getValue());

			// retrieve customer and items
			pbCustomer = orderData.getCustomer();
			List<PropertyBox> pbOrderItems = orderData.getOrderItems();

			// remove all order items before update
			pbOrderItems.forEach(pb -> {
				orderItemService.delete(pb);
			});

		} else {
			pbOrder = PropertyBox.builder(Order.ORDER).build();
			pbOrder.setValue(Order.STATE, OrderState.NEW);
			pbOrder.setValue(Order.PAID, false);
			pbCustomer = PropertyBox.builder(Customer.CUSTOMER).build();
		}

		// customer info
		pbCustomer.setValue(Customer.FULLNAME, customer.getValue());
		pbCustomer.setValue(Customer.PHONE, phone.getValue());
		pbCustomer.setValue(Customer.DETAILS, details.getValue());

		// order info
		pbOrder.setValue(Order.DUE_DATE, dateInput.getValue());
		pbOrder.setValue(Order.DUE_TIME, LocalTime.parse(timeSelect.getValue().getValue()));
		pbOrder.setValue(Order.PICKUP_LOCATION, pickupSelect.getValue());

		// order items
		List<PropertyBox> orderItems = orderItemsMap.entrySet().stream()
				.filter(entry -> entry.getValue().product.getValue() != null).map(entry -> {
					PropertyBox pb = PropertyBox.create(OrderItem.ORDER_ITEM);
					pb.setValue(OrderItem.SEQUENCE, entry.getKey());
					pb.setValue(OrderItem.PRODUCT, entry.getValue().getProductId());
					pb.setValue(OrderItem.QUANTITY, entry.getValue().getQuantity());
					pb.setValue(OrderItem.COMMENT, entry.getValue().getComment());
					return pb;
				}).collect(Collectors.toList());

		// load order data with property boxes
		OrderData orderData = OrderData.builder().customer(pbCustomer).order(pbOrder).orderItems(orderItems).build();

		// insert or update order
		OrderDataService ods = Context.get().resource(OrderDataService.class).get();
		ods.placeOrUpdateOrder(orderData);

		// refresh
		runnable.run();
	}

}
