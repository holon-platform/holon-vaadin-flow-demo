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
package com.holonplatform.vaadin.flow.demo.dialogs;

import com.holonplatform.core.i18n.LocalizationContext;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.PropertyListing;
import com.holonplatform.vaadin.flow.demo.data.OrderData;
import com.holonplatform.vaadin.flow.demo.forms.OrderManageForm;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

public class OrderManageDialog extends AbstractOrderDialog {

	private static final long serialVersionUID = 1L;

	public OrderManageDialog(OrderData orderData, PropertyListing listing) {

		super();

		final Span totalPriceSpan = Components.span().styleName("total-price").text("Total $0.00").build();
		OrderManageForm form = new OrderManageForm(orderData,
				// consumer behaviour
				totalOrderPrice -> totalPriceSpan
						.setText("Total $" + LocalizationContext.require().format(totalOrderPrice, 2)),
				() -> listing.refresh());
		setContent(form);

		add(Components.hl().fullWidth().add(Components.button().text("Cancel").onClick(evt -> close()).build())
				.add(Components.hl().spacing().add(totalPriceSpan)
						.add(Components.button().withThemeVariants(ButtonVariant.LUMO_PRIMARY)
								.icon(VaadinIcon.ARROW_RIGHT).text("Review order").iconAfterText(true).onClick(e -> {
									form.placeOrder();
									close();
								}).build())
						.build())
				.justifyContentMode(JustifyContentMode.BETWEEN).build());
	}

}
