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
package com.holonplatform.vaadin.flow.demo.pages;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.i18n.LocalizationContext;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.core.query.QueryConfigurationProvider;
import com.holonplatform.core.query.QueryFilter;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.Input;
import com.holonplatform.vaadin.flow.components.PropertyInputForm;
import com.holonplatform.vaadin.flow.components.PropertyListing;
import com.holonplatform.vaadin.flow.components.Selectable.SelectionMode;
import com.holonplatform.vaadin.flow.demo.components.ManageableForm;
import com.holonplatform.vaadin.flow.demo.models.Product;
import com.holonplatform.vaadin.flow.demo.root.Menu;
import com.holonplatform.vaadin.flow.demo.services.OrderItemService;
import com.holonplatform.vaadin.flow.demo.services.ProductService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route(value = "products", layout = Menu.class)
public class Products extends VerticalLayout implements QueryConfigurationProvider, ManageableForm {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Datastore datastore;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderItemService orderItemService;

	private PropertyInputForm form;
	private PropertyListing propertyListing;
	private Button btnInsertUpdate;
	private Button btnDiscard;
	private Button btnDelete;
	private Input<String> searchField;

	@PostConstruct
	public void init() {

		LocalizationContext lc = LocalizationContext.require();
		lc.localize(Locale.US);

		form = Components.input.form(Product.PRODUCT).hidden(Product.ID).build();
		form.setEnabled(false);

		searchField = Components.input.string().placeholder("Search").prefixComponent(new Icon(VaadinIcon.SEARCH))
				.valueChangeMode(ValueChangeMode.EAGER).withValueChangeListener(event -> {
					propertyListing.refresh();
				}).build();

		Components.configure(this).fullSize().spacing().withoutMargin()
				// horizontal toolbar
				.add(Components.hl().fullWidth().spacing()
						// search field
						.addAndExpand(searchField.getComponent(), 1d)
						// btn new
						.add(Components.button().text("Add new").icon(VaadinIcon.PLUS)
								.withThemeVariants(ButtonVariant.LUMO_PRIMARY).onClick(event -> {
									clearFields();
									enableForm(true);
									btnInsertUpdate.setText("Add");
									propertyListing.deselectAll();
								}).build())
						.build())

				// horizontal split panel
				.add(Components.hl().fullSize().spacing()
						// user grid
						.addAndExpand(propertyListing = Components.listing.properties(Product.PRODUCT).fullSize()
								.selectionMode(SelectionMode.SINGLE).visibleColumns(Product.NAME, Product.PRICE)
								.resizable(true)
								.withThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS)
								// query filter config
								.dataSource(datastore, Product.TARGET).withQueryConfigurationProvider(this)
								.withDefaultQuerySort(Product.NAME.asc())
								.renderer(Product.PRICE,
										new TextRenderer<>(item -> "$" + lc.format(item.getValue(Product.PRICE), 2)))
								.withItemClickListener(evt -> {
									enableForm(true);
									form.setValue(evt.getItem());
									btnInsertUpdate.setText("Update");
								}).build(), 1d)
						// product form and buttons
						.add(Components.vl().sizeUndefined().fullHeight().withoutPadding().add(form)
								.addAndExpand(new Div(), 1d)
								.add(Components.hl().spacing().add(btnInsertUpdate = Components.button().text("Update")
										.withThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY)
										.onClick(event -> save()).fullWidth().build())
										.add(btnDiscard = Components.button().text("Discard")
												.onClick(event -> discard()).fullWidth().build())
										.add(btnDelete = Components.button().text("Delete")
												.withThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY)
												.onClick(event -> {
													delete();
												}).fullWidth().build())
										.build())
								.build())
						.build());
		enableForm(false);
	}

	@Override
	public void enableForm(boolean enable) {
		form.setEnabled(enable);
		btnDelete.setEnabled(enable);
		btnDiscard.setEnabled(enable);
		btnInsertUpdate.setEnabled(enable);
	}

	@Override
	public void clearFields() {
		form.clear();
	}

	@Override
	public void save() {
		PropertyBox pbProduct = form.getValue();
		OperationResult op = productService.save(pbProduct);
		if (op.getAffectedCount() > 0) {
			propertyListing.refresh();
			propertyListing.select(pbProduct);
		}
	}

	@Override
	public void delete() {
		propertyListing.getFirstSelectedItem().ifPresent(pb -> {
			// check for orders
			if (orderItemService.orderItemsCount(pb.getValue(Product.ID)) < 1) {
				OperationResult op = productService.delete(pb);
				if (op.getAffectedCount() > 0) {
					propertyListing.refresh();
					clearFields();
					enableForm(false);
				}
			} else {
				Notification.show("Can't delete selected product, there are still orders with this product.");
			}
		});
	}

	@Override
	public void discard() {
		propertyListing.getFirstSelectedItem().ifPresent(pb -> {
			form.setValue(pb);
		});
	}

	@Override
	public QueryFilter getQueryFilter() {
		String searchFilter = searchField.getValue();
		if (searchFilter != null && !searchFilter.isEmpty()) {
			return Product.NAME.containsIgnoreCase(searchField.getValue() != null ? searchField.getValue() : "");
		}
		return null;
	}

}
