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
		setWidthFull();

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
