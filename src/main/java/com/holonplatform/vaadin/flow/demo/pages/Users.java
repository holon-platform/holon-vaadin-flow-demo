package com.holonplatform.vaadin.flow.demo.pages;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.holonplatform.auth.Credentials;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.core.query.QueryConfigurationProvider;
import com.holonplatform.core.query.QueryFilter;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.Input;
import com.holonplatform.vaadin.flow.components.PropertyInputForm;
import com.holonplatform.vaadin.flow.components.PropertyListing;
import com.holonplatform.vaadin.flow.components.Selectable.SelectionMode;
import com.holonplatform.vaadin.flow.demo.components.ManageableForm;
import com.holonplatform.vaadin.flow.demo.models.Role;
import com.holonplatform.vaadin.flow.demo.models.User;
import com.holonplatform.vaadin.flow.demo.root.Menu;
import com.holonplatform.vaadin.flow.demo.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route(value = "users", layout = Menu.class)
public class Users extends VerticalLayout implements QueryConfigurationProvider, ManageableForm {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Datastore datastore;
	@Autowired
	private UserService userService;

	private Input<String> searchField;
	private PropertyListing propertyListing;
	private PropertyInputForm form;
	private Input<String> password;
	private Button btnInsertUpdate;
	private Button btnDelete;
	private Button btnDiscard;

	private boolean editMode;

	@PostConstruct
	public void init() {

		searchField = Components.input.string()/*.fullWidth()*/.placeholder("Search").prefixComponent(new Icon(VaadinIcon.SEARCH))
				.withValueChangeListener(event -> propertyListing.refresh()).valueChangeMode(ValueChangeMode.EAGER)
				.build();

		password = Components.input.password().sizeUndefined().build();

		form = Components.input.form(User.USER).hidden(User.ID).hidden(User.USER_ROLE)
				.bind(User.ROLE,
						Components.input.singleSelect(Role.ID).dataSource(datastore, Role.TARGET, Role.ROLE)
								.sizeUndefined().itemCaptionProperty(Role.DESCRIPTION).build())
				.bind(User.PASSWORD, password).build();
		form.setEnabled(false);

		Components.configure(this).spacing().withoutMargin()
				// horizontal toolbar
				.add(Components.hl()/*.fullWidth()*/.spacing()
						// search field
						.addAndExpand(searchField.getComponent(), 1d)
						.add(searchField.getComponent())
						// btn new
						.add(Components.button().text("Add new").icon(VaadinIcon.PLUS)
								.withThemeVariants(ButtonVariant.LUMO_PRIMARY).onClick(event -> {
									clearFields();
									enableForm(true);
									btnInsertUpdate.setText("Add");
									// fieldEmail.focus();
									propertyListing.deselectAll();
									editMode = false;
								}).build())
						.build())
				// horizontal split panel
				.add(Components.hl().fullSize().spacing()
						// user listing
						.addAndExpand(propertyListing = Components.listing.properties(User.USER).fullSize()
								.resizable(true).dataSource(datastore, User.TARGET).withQueryConfigurationProvider(this)
								.withDefaultQuerySort(User.EMAIL.asc())
								.visibleColumns(User.EMAIL, User.NAME, User.USER_ROLE)
								.withThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS)
								.selectionMode(SelectionMode.SINGLE).withItemClickListener(evt -> {
									enableForm(true);
									form.setValue(evt.getItem());
									password.clear();
									btnInsertUpdate.setText("Update");
									editMode = true;
								}).build(), 1d)

						.add(Components.vl().sizeUndefined().fullHeight().withoutPadding().add(form)
								.addAndExpand(new Div(), 1d)
								.add(Components.hl().fullWidth().spacing().add(btnInsertUpdate = Components.button()
										.text("Update")
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

		PropertyBox pbUser = form.getValue();

		// check already used email
		String mail = pbUser.getValue(User.EMAIL);
		if (!editMode) {
			if (!userService.getUserByEmail(mail).isPresent()) {
				saveUser(pbUser);
			} else {
				Notification.show("Cannot use this email account: already used", 2000, Position.BOTTOM_CENTER);
			}
		} else {
			saveUser(pbUser);
		}
	}

	private void saveUser(PropertyBox pbUser) {
		// encode password
		final String encoded = Credentials.encoder().secret(pbUser.getValue(User.PASSWORD))
				.hashAlgorithm(Credentials.Encoder.HASH_SHA_512).buildAndEncodeBase64();

		pbUser.setValue(User.PASSWORD, encoded);

		OperationResult operationResult = userService.save(pbUser);
		if (operationResult.getAffectedCount() > 0) {
			propertyListing.refresh();
			propertyListing.select(pbUser);
		}
	}

	@Override
	public void delete() {
		propertyListing.getFirstSelectedItem().ifPresent(propertyBox -> {
			OperationResult op = userService.delete(propertyBox);
			if (op.getAffectedCount() > 0) {
				propertyListing.refresh();
				clearFields();
			}
		});
	}

	@Override
	public void discard() {
		propertyListing.getFirstSelectedItem().ifPresent(propertyBox -> {
			form.setValue(propertyBox);
			password.clear();
		});
	}

	@Override
	public QueryFilter getQueryFilter() {
		String searchFilter = searchField.getValue();
		if (searchFilter != null && !searchFilter.isEmpty()) {
			return User.NAME.containsIgnoreCase(searchField.getValue() != null ? searchField.getValue() : "")
					.or(User.EMAIL.containsIgnoreCase(searchField.getValue() != null ? searchField.getValue() : ""));
		}
		return null;
	}

}
