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

import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.StringProperty;

public interface Customer {
	
	public static final NumericProperty<Long> ID = NumericProperty.create("id", Long.class);
	public static final StringProperty FULLNAME = StringProperty.create("fullname");
	public static final StringProperty PHONE = StringProperty.create("phone_number");
	public static final StringProperty DETAILS = StringProperty.create("details");

	public static final PropertySet<?> CUSTOMER = PropertySet.of(ID, FULLNAME, PHONE, DETAILS);
	public static final DataTarget<?> TARGET = DataTarget.named("customers");
	
}
