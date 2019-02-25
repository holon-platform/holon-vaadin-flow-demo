package com.holonplatform.vaadin.flow.demo.root;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.holonplatform.auth.AuthContext;
import com.holonplatform.auth.Authentication;
import com.holonplatform.auth.Authentication.AuthenticationListener;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.demo.enums.UserRole;
import com.holonplatform.vaadin.flow.navigator.Navigator;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.UIScope;

@ParentLayout(MainLayout.class)
@Component
@UIScope
public class Menu extends HorizontalLayout implements RouterLayout, AuthenticationListener {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AuthContext authContext;

	private Button btnStorefront;
	private Button btnDashboard;
	private Button btnUsers;
	private Button btnProducts;

	public Menu() {
		super();
		setSizeFull();
		setSpacing(false);
		getStyle().set("overflow", "hidden");
	}

	@PostConstruct
	private void init() {

		authContext.addAuthenticationListener(this);

		Image holonLogo = new Image("frontend/images/holon-logo.png", "Holon Logo");
		holonLogo.setWidth("80%");

		Label lblArtisan = new Label();
		lblArtisan.getElement().setProperty("innerHTML", "Bakery");
		lblArtisan.getStyle().set("font-size", "xx-large");

		VerticalLayout vl = Components.vl().width("280px").height("100%").styleName("menu")
				.add(Components.vl().withoutPadding().add(lblArtisan, btnStorefront = Components.button()
						.text("Storefront").withThemeVariants(ButtonVariant.LUMO_LARGE).width("100%").onClick(evt -> {
							Navigator.get().navigateTo("");
							resetStyles();
							btnStorefront.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
						}).build(), btnDashboard = Components.button().text("Dashboard")
								.withThemeVariants(ButtonVariant.LUMO_LARGE).width("100%").onClick(evt -> {
									Navigator.get().navigateTo("dashboard");
									resetStyles();
									btnDashboard.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
								}).build(),
						btnUsers = Components.button().text("Users").withThemeVariants(ButtonVariant.LUMO_LARGE)
								.width("100%").onClick(evt -> {
									Navigator.get().navigateTo("users");
									resetStyles();
									btnUsers.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
								}).build(),
						btnProducts = Components.button().text("Products").withThemeVariants(ButtonVariant.LUMO_LARGE)
								.width("100%").onClick(evt -> {
									Navigator.get().navigateTo("products");
									resetStyles();
									btnProducts.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
								}).build(),
						Components.button().text("Logout").withThemeVariants(ButtonVariant.LUMO_LARGE).width("100%")
								.onClick(evt -> {
									logout();
									// Navigator.get().navigateTo("");
								}).build(),

						Components.button().text("Test UI").withThemeVariants(ButtonVariant.LUMO_LARGE).width("100%")
								.onClick(evt -> {
									Navigator.get().navigateTo("testui");
									resetStyles();
									btnProducts.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
								}).build()

				).build())
				.add(Components.vl().withoutPadding().add(holonLogo).align(holonLogo, Alignment.CENTER).build())
				.justifyContentMode(JustifyContentMode.BETWEEN).align(lblArtisan, Alignment.CENTER).build();

		add(vl);
		btnStorefront.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	}

	private void logout() {
		authContext.unauthenticate();
		VaadinSession.getCurrent().getSession().invalidate();
		UI.getCurrent().getPage().reload();
	}

	public void resetStyles() {
		btnStorefront.removeThemeVariants(ButtonVariant.LUMO_PRIMARY);
		btnDashboard.removeThemeVariants(ButtonVariant.LUMO_PRIMARY);
		btnUsers.removeThemeVariants(ButtonVariant.LUMO_PRIMARY);
		btnProducts.removeThemeVariants(ButtonVariant.LUMO_PRIMARY);
	}

	@Override
	public void onAuthentication(Authentication authentication) {
		if (authentication != null) {
			btnStorefront.setVisible(authContext.isPermittedAny(UserRole.ADMIN.getRole(), UserRole.BARISTA.getRole()));
			btnDashboard.setVisible(authContext.isPermittedAny(UserRole.ADMIN.getRole(), UserRole.BARISTA.getRole()));
			btnUsers.setVisible(authContext.isPermitted(UserRole.ADMIN.getRole()));
			btnProducts.setVisible(authContext.isPermitted(UserRole.ADMIN.getRole()));
		}
	}

}
