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

import com.holonplatform.core.Context;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.query.QueryConfigurationProvider;
import com.holonplatform.core.query.QueryFilter;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.PropertyListing;
import com.holonplatform.vaadin.flow.demo.dialogs.OrderReviewDialog;
import com.holonplatform.vaadin.flow.demo.models.Order;
import com.holonplatform.vaadin.flow.demo.renderers.OrderRenderer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.grid.GridVariant;

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
					OrderReviewDialog omd = new OrderReviewDialog(evt.getItem(), listing);
					omd.open();
				}).build();

		getElement().appendChild(listing.getComponent().getElement());
	}
	
	public PropertyListing getPropertyListing () {
		return listing;
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
