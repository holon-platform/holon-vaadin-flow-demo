package com.holonplatform.vaadin.flow.demo.models;

import com.holonplatform.core.Context;
import com.holonplatform.core.Validator;
import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.i18n.Localizable;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.StringProperty;
import com.holonplatform.core.property.VirtualProperty;

public interface User {

	public static final NumericProperty<Long> ID = NumericProperty.create("id", Long.class);
	public static final StringProperty EMAIL = StringProperty.create("email").message("Email")
			.withValidator(Validator.notBlank(Localizable.builder().message("Email is required").build()));
	public static final StringProperty NAME = StringProperty.create("name").message("Name")
			.withValidator(Validator.notBlank(Localizable.builder().message("User name is required").build()));
	public static final StringProperty PASSWORD = StringProperty.create("password").message("Password")
			.withValidator(Validator.notBlank(Localizable.builder().message("Password is required").build()));
	public static final StringProperty ROLE = StringProperty.create("role").message("Role")
			.withValidator(Validator.notBlank(Localizable.builder().message("User role is required").build()));

	public static final VirtualProperty<?> USER_ROLE = VirtualProperty.create(String.class, row -> {
		Datastore ds = Context.get().resource(Datastore.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve Datastore from Context."));
		return ds.query(Role.TARGET).filter(Role.ID.eq(row.getValue(ROLE))).findOne(Role.DESCRIPTION).orElse("");
	}).message("Role");

	public static final PropertySet<?> USER = PropertySet.builderOf(ID, EMAIL, NAME, ROLE, USER_ROLE, PASSWORD)
			.identifier(ID).build();
	public static final DataTarget<String> TARGET = DataTarget.named("users");
	
	// User details params
	public static final String USER_DETAIL_NAME = "USER_DETAIL_NAME";
	public static final String USER_DETAIL_ID = "USER_DETAIL_ID";

}
