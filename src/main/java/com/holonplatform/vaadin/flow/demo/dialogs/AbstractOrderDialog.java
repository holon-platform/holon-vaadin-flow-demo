package com.holonplatform.vaadin.flow.demo.dialogs;

import com.holonplatform.vaadin.flow.components.Components;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;

public abstract class AbstractOrderDialog extends Dialog implements HasTheme {

	private static final long serialVersionUID = 1L;

	private Div content;

	public AbstractOrderDialog() {
		// default size
		setWidth("700px");
		// custome css style
		getThemeNames().add("orders");
		// make content scrollable
		content = Components.div().build();
		content.setSizeFull();
		content.getStyle().set("overflow", "auto");

		add(content);
	}

	protected void setContent(Component component) {
		content.add(component);
	}

}
