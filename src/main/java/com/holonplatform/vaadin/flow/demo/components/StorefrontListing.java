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
package com.holonplatform.vaadin.flow.demo.components;

import java.util.function.Supplier;

import com.holonplatform.artisan.vaadin.flow.components.Window;
import com.holonplatform.artisan.vaadin.flow.components.builders.WindowBuilder;
import com.holonplatform.core.Context;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.i18n.LocalizationContext;
import com.holonplatform.core.query.QueryConfigurationProvider;
import com.holonplatform.core.query.QueryFilter;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.PropertyListing;
import com.holonplatform.vaadin.flow.demo.data.OrderData;
import com.holonplatform.vaadin.flow.demo.forms.OrderReview;
import com.holonplatform.vaadin.flow.demo.forms.OrderReviewForm;
import com.holonplatform.vaadin.flow.demo.models.Order;
import com.holonplatform.vaadin.flow.demo.renderers.OrderRenderer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

@Tag(value = "storefront")
public class StorefrontListing extends Component implements QueryConfigurationProvider, HasSize, HasStyle {

	private static final long serialVersionUID = 1L;

	private Supplier<QueryFilter> filterSupplier;
	private PropertyListing listing;

	public StorefrontListing() {
		super();
		setSizeFull();

		Datastore datastore = Context.get().resource(Datastore.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve Datastore from Context."));

		listing = Components.listing.properties(Order.ORDER).fullSize().styleName("storefront")
				.withThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS).fullSize()
				.resizable(true).header(Order.ID, "Orders").visibleColumns(Order.ID)
				.renderer(Order.ID, new OrderRenderer())
				// customer name filter
				.dataSource(datastore, Order.TARGET).withQueryConfigurationProvider(this)
				.withDefaultQuerySort(Order.DUE_DATE.asc()).withItemClickListener(evt -> {
					Span totalEditPriceSpan = Components.span().styleName("total-price").text("Total $0.00").build();
					OrderReview or = new OrderReview(evt.getItem(), price -> totalEditPriceSpan
							.setText("Total $" + LocalizationContext.require().format(price, 2)));
					WindowBuilder wndBuilder = Window.builder().width("800px").content(or);
					Window wnd = wndBuilder.build();
					// footer
					wndBuilder.withFooterComponent(Components.hl().fullWidth()
							.add(Components.button().text("Cancel").onClick(e -> wnd.close()).build())
							.add(Components.hl().spacing().add(totalEditPriceSpan)
									.add(Components.button().withThemeVariants(ButtonVariant.LUMO_PRIMARY)
											.icon(VaadinIcon.EDIT).text("Edit order").iconAfterText(true)
											.onClick(event -> {
												wnd.close();
												editOrder(or.getOrderData());
											}).build())
									.build())
							.justifyContentMode(JustifyContentMode.BETWEEN).build()).build();
					wnd.open();
				}).build();

		getElement().appendChild(listing.getComponent().getElement());
	}

	private void editOrder(OrderData orderData) {
		final Span totalPriceSpan = Components.span().styleName("total-price").text("Total $0.00").build();
		OrderReviewForm form = new OrderReviewForm(orderData,
				// consumer behaviour
				totalOrderPrice -> totalPriceSpan
						.setText("Total $" + LocalizationContext.require().format(totalOrderPrice, 2)),
				() -> listing.refresh());

		WindowBuilder wndBuilder = Window.builder().width("800px").content(form);
		Window wnd = wndBuilder.build();
		wndBuilder.content(form)
				// footer
				.withFooterComponent(Components.hl().fullWidth()
						.add(Components.button().text("Cancel").onClick(evt -> wnd.close()).build())
						.add(Components.hl().spacing().add(totalPriceSpan)
								.add(Components.button().withThemeVariants(ButtonVariant.LUMO_PRIMARY)
										.icon(VaadinIcon.ARROW_RIGHT).text("Review order").iconAfterText(true)
										.onClick(e -> {
											form.placeOrder();
											wnd.close();
										}).build())
								.build())
						.justifyContentMode(JustifyContentMode.BETWEEN).build())
				.width("700px").build().open();
	}

	public void refresh() {
		listing.refresh();
	}

	public void setFilterSupplier(Supplier<QueryFilter> filterSupplier) {
		this.filterSupplier = filterSupplier;
	}

	@Override
	public QueryFilter getQueryFilter() {
		return filterSupplier != null ? filterSupplier.get() : null;
	}
}
