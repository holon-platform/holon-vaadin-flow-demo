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
