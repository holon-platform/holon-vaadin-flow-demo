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

import java.time.LocalDateTime;

import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.StringProperty;
import com.holonplatform.core.property.TemporalProperty;

public interface OrderHistory {

	public static final NumericProperty<Long> ORDER_ID = NumericProperty.create("order_id", Long.class);
	public static final NumericProperty<Long> ID = NumericProperty.create("id", Long.class);
	public static final StringProperty MESSAGE = StringProperty.create("message");
	public static final TemporalProperty<LocalDateTime> TIMESTAMP = TemporalProperty.create("timestamp", LocalDateTime.class);
	public static final NumericProperty<Long> CREATED_BY = NumericProperty.create("created_by", Long.class);

	public static final DataTarget<?> TARGET = DataTarget.named("order_history");

	public static final PropertySet<?> ORDER_HISTORY = PropertySet.of(ORDER_ID, ID, MESSAGE, TIMESTAMP, CREATED_BY);

}
