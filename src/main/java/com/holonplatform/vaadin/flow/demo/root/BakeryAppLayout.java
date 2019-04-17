package com.holonplatform.vaadin.flow.demo.root;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.holonplatform.auth.AuthContext;
import com.holonplatform.auth.Authentication;
import com.holonplatform.auth.Authentication.AuthenticationListener;
import com.holonplatform.auth.annotations.Authenticate;
import com.holonplatform.http.HttpHeaders;
import com.holonplatform.vaadin.flow.demo.enums.UserRole;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.UIScope;

@Authenticate(schemes = HttpHeaders.SCHEME_BASIC, redirectURI = "login")
@ParentLayout(MainLayout.class)
@Component
@UIScope
public class BakeryAppLayout extends AppLayout implements RouterLayout, AuthenticationListener {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AuthContext authContext;

	private AppLayoutMenuItem storefront;
	private AppLayoutMenuItem dashboard;
	private AppLayoutMenuItem users;
	private AppLayoutMenuItem products;

	public BakeryAppLayout() {
		super();
	}

	@PostConstruct
	private void init() {

		authContext.addAuthenticationListener(this);

		AppLayoutMenu menu = createMenu();

		Image holonLogo = new Image("frontend/images/holon-logo.png", "Holon Logo");
		holonLogo.setHeight("50px");

		setBranding(holonLogo);

		storefront = new AppLayoutMenuItem(VaadinIcon.EDIT.create(), "Storefront", "");
		storefront.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
		dashboard = new AppLayoutMenuItem(VaadinIcon.DASHBOARD.create(), "Dashboard", "dashboard");
		dashboard.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
		users = new AppLayoutMenuItem(VaadinIcon.GROUP.create(), "Users", "users");
		users.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
		products = new AppLayoutMenuItem(VaadinIcon.CUBES.create(), "Products", "products");
		products.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
		AppLayoutMenuItem logout = new AppLayoutMenuItem(VaadinIcon.ARROW_RIGHT.create(), "Logout", evt -> logout());
		logout.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);

		menu.addMenuItems(storefront, dashboard, users, products, logout);
		menu.selectMenuItem(storefront);
	}

	private void logout() {
		authContext.unauthenticate();
		VaadinSession.getCurrent().getSession().invalidate();
		UI.getCurrent().getPage().reload();
	}

	@Override
	public void onAuthentication(Authentication authentication) {
		if (authentication != null) {
			storefront.setVisible(authContext.isPermittedAny(UserRole.ADMIN.getRole(), UserRole.BARISTA.getRole()));
			dashboard.setVisible(authContext.isPermittedAny(UserRole.ADMIN.getRole(), UserRole.BARISTA.getRole()));
			users.setVisible(authContext.isPermitted(UserRole.ADMIN.getRole()));
			products.setVisible(authContext.isPermitted(UserRole.ADMIN.getRole()));
		}
	}
}
