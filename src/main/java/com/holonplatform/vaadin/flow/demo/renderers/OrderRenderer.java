package com.holonplatform.vaadin.flow.demo.renderers;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.holonplatform.core.Context;
import com.holonplatform.core.i18n.LocalizationContext;
import com.holonplatform.core.i18n.TemporalFormat;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.demo.enums.OrderState;
import com.holonplatform.vaadin.flow.demo.models.Order;
import com.holonplatform.vaadin.flow.demo.models.OrderItem;
import com.holonplatform.vaadin.flow.demo.services.OrderItemService;
import com.holonplatform.vaadin.flow.demo.services.ProductService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public class OrderRenderer extends ComponentRenderer<HorizontalLayout, PropertyBox> {

	private static final long serialVersionUID = 1L;

	@Override
	public HorizontalLayout createComponent(PropertyBox item) {

		HorizontalLayout wrapper = Components.hl().styleName("storefront-row").build();

		// status
		Div statusDiv = Components.div().styleName("status-div").build();

		if (OrderState.DELIVERED.equals(item.getValue(Order.STATE))) {
			Icon icon = new Icon(VaadinIcon.CHECK);
			icon.addClassName("icon-delivered");
			statusDiv.add(icon);
		} else {
			statusDiv.add(Components.span().text(item.getValue(Order.STATE).name())
					.styleNames("status", item.getValue(Order.STATE).name().toLowerCase()).build());
		}

		wrapper.add(statusDiv);

		Div orderDateDiv = Components.div().styleName("order-date").build();
		// date
		H3 date = Components.h3().styleName("order-day")
				.text(DateTimeFormatter.ofPattern("dd MMM").withLocale(Locale.US).format(item.getValue(Order.DUE_DATE)))
				.build();
		orderDateDiv.add(date);
		// time
		orderDateDiv.add(Components.div()
				.text(LocalizationContext.require().format(item.getValue(Order.DUE_TIME), null, TemporalFormat.SHORT))
				.build());

		// store
		orderDateDiv.add(Components.div().text(item.getValue(Order.PICKUP_LOCATION_DESCRIPTION)).build());

		wrapper.add(orderDateDiv);

		// customer
		H3 customerName = Components.h3().styleName("customer").text(item.getValue(Order.CUSTOMER_FULLNAME)).build();

		Div customerDiv = Components.div().build();
		customerDiv.getStyle().set("flex-grow", "1");
		customerDiv.add(customerName);

		wrapper.add(customerDiv);

		// product list
		FlexLayout itemsDiv = new FlexLayout();
		itemsDiv.addClassName("items");

		ProductService productService = Context.get().resource(ProductService.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve ProductService from Context."));

		OrderItemService orderItemService = Context.get().resource(OrderItemService.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve OrderItemService from Context."));

		List<PropertyBox> pbList = orderItemService.getOrderItems(item.getValue(Order.ID), OrderItem.PRODUCT,
				OrderItem.QUANTITY);

		if (pbList != null && !pbList.isEmpty()) {
			for (PropertyBox pb : pbList) {
				Span itemQuantity = Components.span().text(pb.getValue(OrderItem.QUANTITY).toString())
						.styleName("count").build();
				Optional<String> productName = productService.getName(pb.getValue(OrderItem.PRODUCT));
				Div productDiv = Components.div().text(productName.orElse(pb.getValue(OrderItem.PRODUCT).toString()))
						.build();
				Div itemDiv = Components.div().build();
				itemDiv.addClassName("item");
				itemDiv.add(itemQuantity, productDiv);
				itemsDiv.add(itemDiv);

			}
			customerDiv.add(itemsDiv);
		}

		return wrapper;
	}

}
