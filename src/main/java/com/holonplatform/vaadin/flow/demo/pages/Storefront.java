package com.holonplatform.vaadin.flow.demo.pages;

import java.time.LocalDate;

import com.holonplatform.artisan.vaadin.flow.components.Window;
import com.holonplatform.artisan.vaadin.flow.components.builders.WindowBuilder;
import com.holonplatform.core.datastore.relational.SubQuery;
import com.holonplatform.core.i18n.LocalizationContext;
import com.holonplatform.core.query.QueryFilter;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.Input;
import com.holonplatform.vaadin.flow.demo.components.StorefrontListing;
import com.holonplatform.vaadin.flow.demo.forms.OrderReviewForm;
import com.holonplatform.vaadin.flow.demo.models.Customer;
import com.holonplatform.vaadin.flow.demo.models.Order;
import com.holonplatform.vaadin.flow.demo.root.Menu;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@HtmlImport("frontend://com-holonplatform-demo-storefront-styles.html")
@Route(value = "", layout = Menu.class)
public class Storefront extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private Input<String> searchField;
	private Span totalPriceSpan;

	public Storefront() {
		super();
		StorefrontListing listing = new StorefrontListing();
		listing.setFilterSupplier(() -> buildFilter());

		searchField = Components.input.string().placeholder("Search").prefixComponent(new Icon(VaadinIcon.SEARCH))
				.fullWidth().withValueChangeListener(evt -> {
					listing.setFilterSupplier(() -> {
						return buildFilter();
					});
					listing.refresh();
				}).valueChangeMode(ValueChangeMode.EAGER).build();

		Components.configure(this).fullWidth().spacing().withoutMargin()
				// horizontal toolbar
				.add(Components.hl().fullWidth().spacing()
						// search field
						.addAndExpand(searchField.getComponent(), 1d)
						// btn new
						.add(Components.button().text("New order").styleName("storefront-btn").icon(VaadinIcon.PLUS)
								.withThemeVariants(ButtonVariant.LUMO_PRIMARY).onClick(event -> {
									OrderReviewForm form = new OrderReviewForm(null,
											// consumer behaviour
											totalOrderPrice -> totalPriceSpan.setText("Total $"
													+ LocalizationContext.require().format(totalOrderPrice, 2)),
											() -> listing.refresh());
									WindowBuilder wndBuilder = Window.builder().content(form);
									Window wnd = wndBuilder.build();
									wndBuilder
											// footer
											.withFooterComponent(Components.hl().fullWidth()
													.add(Components.button().text("Cancel").build())
													.add(Components.hl().spacing()
															.add(totalPriceSpan = Components.span()
																	.styleName("total-price").text("Total $0.00")
																	.build())
															.add(Components.button().icon(VaadinIcon.ARROW_RIGHT)
																	.text("Review order")
																	.withThemeVariants(ButtonVariant.LUMO_PRIMARY)
																	.iconAfterText(true).onClick(evt -> {
																		form.placeOrder();
																		wnd.close();
																	}).build())
															.build())
													.justifyContentMode(JustifyContentMode.BETWEEN).build())
											.width("700px").build().open();
								}).build())
						.build())
				.add(listing);
	}

	private QueryFilter buildFilter() {
		QueryFilter filter = Order.DUE_DATE.goe(LocalDate.now());

		// customer filter
		String customerName = searchField.getValue();
		if (customerName != null) {
			QueryFilter customerFilter = SubQuery.create().target(Customer.TARGET).filter(
					// NB: used to set order alias for Order.customer field!
					Order.TARGET.property(Order.CUSTOMER).eq(Customer.ID)
							.and(Customer.FULLNAME.containsIgnoreCase(customerName)))
					.exists();
			filter = filter.and(customerFilter);
		}
		return filter;
	}
}
