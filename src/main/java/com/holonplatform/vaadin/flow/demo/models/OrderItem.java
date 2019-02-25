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
package com.holonplatform.vaadin.flow.demo.models;

import com.holonplatform.core.Context;
import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.StringProperty;
import com.holonplatform.core.property.VirtualProperty;

public interface OrderItem {

	public static final NumericProperty<Long> SEQUENCE = NumericProperty.create("sequence", Long.class);
	public static final NumericProperty<Long> ORDER = NumericProperty.create("orderid", Long.class);
	public static final NumericProperty<Integer> QUANTITY = NumericProperty.create("quantity", Integer.class);
	public static final NumericProperty<Integer> PRODUCT = NumericProperty.create("product", Integer.class);
	public static final StringProperty COMMENT = StringProperty.create("comment");

	// product name
	public static final VirtualProperty<String> PRODUCT_NAME = VirtualProperty.create(String.class, propertyBox -> {
		Datastore ds = Context.get().resource(Datastore.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve Datastore from Context."));
		return ds.query(Product.TARGET).filter(Product.ID.eq(propertyBox.getValue(OrderItem.PRODUCT)))
				.findOne(Product.NAME).orElse("");
	});

	// product price
	public static final VirtualProperty<Double> PRODUCT_PRICE = VirtualProperty.create(Double.class, propertyBox -> {
		Datastore ds = Context.get().resource(Datastore.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve Datastore from Context."));
		return ds.query(Product.TARGET).filter(Product.ID.eq(propertyBox.getValue(OrderItem.PRODUCT)))
				.findOne(Product.PRICE).orElse(new Double(0));
	});

	public static final PropertySet<?> ORDER_ITEM = PropertySet.of(SEQUENCE, ORDER, QUANTITY, PRODUCT, COMMENT,
			PRODUCT_NAME, PRODUCT_PRICE);
	public static final DataTarget<?> TARGET = DataTarget.named("orderitems");

}
