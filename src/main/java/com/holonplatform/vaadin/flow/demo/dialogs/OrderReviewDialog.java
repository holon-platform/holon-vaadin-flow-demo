package com.holonplatform.vaadin.flow.demo.dialogs;

import com.holonplatform.core.i18n.LocalizationContext;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.PropertyListing;
import com.holonplatform.vaadin.flow.demo.data.OrderData;
import com.holonplatform.vaadin.flow.demo.forms.OrderReview;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

public class OrderReviewDialog extends AbstractOrderDialog {

	private static final long serialVersionUID = 1L;

	private PropertyListing listing;

	public OrderReviewDialog(PropertyBox pb, PropertyListing listing) {

		this.listing = listing;

		Span totalEditPriceSpan = Components.span().styleName("total-price").text("Total $0.00").build();
		OrderReview or = new OrderReview(pb,
				// consumer behaviour
				price -> totalEditPriceSpan.setText("Total $" + LocalizationContext.require().format(price, 2)));

		setContent(or);

		add(Components.hl().fullWidth().add(Components.button().text("Cancel").onClick(e -> close()).build())
				.add(Components.hl().spacing().add(totalEditPriceSpan)
						.add(Components.button().withThemeVariants(ButtonVariant.LUMO_PRIMARY).icon(VaadinIcon.EDIT)
								.text("Edit order").iconAfterText(true).onClick(event -> {
									close();
									editOrder(or.getOrderData());
								}).build())
						.build())
				.justifyContentMode(JustifyContentMode.BETWEEN).build());

	}

	private void editOrder(OrderData orderData) {
		OrderManageDialog ord = new OrderManageDialog(orderData, listing);
		ord.open();
	}

}
