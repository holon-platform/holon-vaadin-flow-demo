package com.holonplatform.vaadin.flow.demo.pages;

import org.springframework.beans.factory.annotation.Autowired;

import com.holonplatform.auth.AuthContext;
import com.holonplatform.auth.AuthenticationToken;
import com.holonplatform.auth.exceptions.AuthenticationException;
import com.holonplatform.core.Validator;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.ValidatableInput;
import com.holonplatform.vaadin.flow.navigator.Navigator;
import com.holonplatform.vaadin.flow.navigator.annotations.OnShow;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@HtmlImport("frontend://styles/shared-styles.html")
@Route(value = "login")
public class Login extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AuthContext authContext;

	private ValidatableInput<String> usn;
	private ValidatableInput<String> pwd;

	public Login() {
		super();
		Components.configure(this).fullSize().spacing().styleName("login-background")
				.add(Components.vl().width("60%").add(Components.vl().styleName("login-logo-background").spacing()
						.add(Components.h1().text("Holon Bakery Demo").build())
						.add(Components.label().sizeUndefined().text("admin@holon-platform.com + admin").build())
						.add(Components.label().sizeUndefined().text("barista@holon-platform.com + barista")
								.build())
						.build()).add(
								Components
										.hl().fullWidth().styleName(
												"login-input")
										.padding().spacing(true).add(
												usn = ValidatableInput
														.builder(Components.input.string().label("Username").required()
																.prefixComponent(new Icon(VaadinIcon.USER))

																.withValue("admin@holon-platform.com")

																.styleName("userfield").blankValuesAsNull(
																		true)
																.fullWidth().build())
														.validateOnValueChange(false).withValidator(
																Validator.notBlank())
														.build())
										.add(pwd = ValidatableInput
												.builder(Components.input.secretString().label("Password").required()
														.prefixComponent(new Icon(VaadinIcon.LOCK))

														.withValue("admin")

														.styleName("passwordfield").blankValuesAsNull(true).fullWidth()
														.build())
												.validateOnValueChange(false).withValidator(Validator.notBlank())
												.build())
										.addAndAlign(Components.button().text("Sign in").width("50%")
												.withThemeVariants(ButtonVariant.LUMO_PRIMARY).onClick(e -> login())
												.build(), Alignment.BASELINE)
										.build())
						.build())
				.justifyContentMode(JustifyContentMode.CENTER).alignItems(Alignment.CENTER);
		usn.focus();
	}

	@OnShow
	private void onShow() {
		// if already logged, redirect to default
		if (authContext.isAuthenticated()) {
			Navigator.get().navigateToDefault();
		}
	}

	private void login() {
		// validate
		if (usn.isValid() && pwd.isValid()) {
			try {
				// authenticate
				authContext.authenticate(AuthenticationToken.accountCredentials(usn.getValue(), pwd.getValue()));
				// redirect to previous request view or to default view if none
				Navigator.get().navigateToDefault();
			} catch (AuthenticationException ae) {
				Notification.show("Invalid credentials", 3000, Position.MIDDLE);
			}
		}
	}

}
