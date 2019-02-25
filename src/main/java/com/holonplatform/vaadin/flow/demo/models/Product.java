package com.holonplatform.vaadin.flow.demo.models;

import com.holonplatform.core.Validator;
import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.i18n.Localizable;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.Property;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.StringProperty;

public interface Product {

	public static final NumericProperty<Integer> ID = NumericProperty.create("id", Integer.class);
	public static final StringProperty NAME = StringProperty.create("name").message("Name")
			.withValidator(Validator.notBlank(Localizable.builder().message("Product name is required").build()));
	public static final NumericProperty<Double> PRICE = NumericProperty.create("price", Double.class).message("Price")
			.withValidator(Validator.notNull(Localizable.builder().message("Product price is required").build()));

	public static final PropertySet<Property<?>> PRODUCT = PropertySet.builderOf(ID, NAME, PRICE).identifier(ID)
			.build();
	public static final DataTarget<?> TARGET = DataTarget.named("products");

}
